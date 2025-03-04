package ssafy.sorhy.repository.gameresult;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.domain.gameresult.QGameResult;
import ssafy.sorhy.domain.gameresult.Team;
import ssafy.sorhy.service.gameresult.dto.OtherUserDto;

import java.util.List;

import static ssafy.sorhy.domain.game.QGame.*;
import static ssafy.sorhy.domain.gameresult.QGameResult.gameResult;
import static ssafy.sorhy.domain.user.QUser.user;

@RequiredArgsConstructor
public class GameResultRepositoryImpl implements GameResultRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GameResult> getGameRecordInfo(Long userId, Pageable pageable) {
        QGameResult subGameResult = new QGameResult("gameResultSub");
        List<GameResult> content = queryFactory
                .selectFrom(gameResult)
                .join(gameResult.game, game).fetchJoin()
                .join(gameResult.user, user).fetchJoin()
                .where(
                        gameResult.game.id.in(
                                JPAExpressions
                                        .select(subGameResult.game.id)
                                        .from(subGameResult)
                                        .where(subGameResult.user.id.eq(userId))
                        )
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(gameResult.count())
                .from(gameResult)
                .where(
                        gameResult.game.id.in(
                                JPAExpressions
                                        .select(subGameResult.game.id)
                                        .from(subGameResult)
                                        .where(subGameResult.user.id.eq(userId))
                        )
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
