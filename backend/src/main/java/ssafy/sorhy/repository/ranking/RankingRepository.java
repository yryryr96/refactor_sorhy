package ssafy.sorhy.repository.ranking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.ranking.Ranking;
import ssafy.sorhy.domain.user.User;

import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Optional<Ranking> findByUserAndGameTitle(User user, GameTitle gameTitle);

    @Query("select r from Ranking r " +
            "where r.gameTitle = :gameTitle " +
            "order by r.score desc")
    Page<Ranking> findByGameTitleOrderByDesc(GameTitle gameTitle, Pageable pageable);
}
