package ssafy.sorhy.repository.article;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.domain.article.QArticle;
import ssafy.sorhy.domain.article.SearchCondition;
import ssafy.sorhy.domain.user.User;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static ssafy.sorhy.domain.article.Category.*;
import static ssafy.sorhy.domain.article.QArticle.article;
import static ssafy.sorhy.domain.user.QUser.user;

@RequiredArgsConstructor
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Article> getAllArticlesByCategory(User user, Category category, Pageable pageable) {
        List<Article> content = queryFactory
                .selectFrom(article)
                .where(
                        categoryEq(category),
                        companyEq(category, user)
                )
                .orderBy(article.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .from(article)
                .where(
                        categoryEq(category),
                        companyEq(category, user)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Article> searchArticleByCondition(User user,
                                                  SearchCondition condition,
                                                  Category category,
                                                  String keyword,
                                                  Pageable pageable) {

        List<Article> content = queryFactory
                .selectFrom(article)
                .where(
                        categoryEq(category),
                        companyEq(category, user),
                        searchConditionEq(condition, keyword)
                )
                .orderBy(article.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .from(article)
                .where(
                        categoryEq(category),
                        companyEq(category, user),
                        searchConditionEq(condition, keyword)
                );


        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Article> getHotArticles(User user, Category category, Pageable pageable) {

        List<Article> content = queryFactory
                .selectFrom(article)
                .where(
                        categoryEq(category),
                        companyEq(category, user)
                )
                .orderBy(article.viewCount.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .from(article)
                .where(
                        categoryEq(category),
                        companyEq(category, user)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Article> getCurrentIssueArticles(Pageable pageable) {
        List<Article> content = queryFactory
                .selectFrom(article)
                .where(
                        article.category.ne(COMPANY)
                )
                .orderBy(article.viewCount.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .from(article)
                .where(
                        article.category.ne(COMPANY)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Long countByNickname(String nickname) {
        return queryFactory
                .select(article.count())
                .from(article)
                .innerJoin(article.user, user)
                .on(article.user.eq(user))
                .where(user.nickname.eq(nickname))
                .fetchOne();
    }

    private BooleanExpression categoryEq(Category category) {
        return category != null ? article.category.eq(category) : null;
    }

    private BooleanExpression companyEq(Category category, User user) {
        return isCompany(category) ? article.user.company.eq(user.getCompany()) : null;
    }

    private BooleanExpression searchConditionEq(SearchCondition searchCondition, String keyword) {
        if (!hasText(keyword) || searchCondition == null) {
            return null;  // 검색어가 없거나 조건이 없으면 필터링 안 함
        }

        QArticle article = QArticle.article;

        return switch (searchCondition) {
            case TITLE -> article.title.containsIgnoreCase(keyword);
            case CONTENT -> article.content.containsIgnoreCase(keyword);
            case NICKNAME -> article.user.nickname.containsIgnoreCase(keyword);
            default -> null;
        };
    }
}
