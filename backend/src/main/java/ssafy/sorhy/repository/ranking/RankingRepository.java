package ssafy.sorhy.repository.ranking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.ranking.Ranking;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.service.ranking.dto.CompanyRankDto;

import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Optional<Ranking> findByUserAndGameTitle(User user, GameTitle gameTitle);

    @Query("select r from Ranking r " +
            "where r.gameTitle = :gameTitle " +
            "order by r.score desc")
    Page<Ranking> findRankingByGameTitleOrderByDesc(GameTitle gameTitle, Pageable pageable);

    @Query(
            "select c.companyName as companyName, u.nickname as companyFirstRankUser, c.companyScore as companyScore " +
                    "from User u " +
                    "join Company c " +
                    "on c.id = u.company.id " +
                    "where u.totalScore = (select max(u2.totalScore) from User u2 where u2.company.id = c.id) " +
                    "order by c.companyScore desc"
    )
    List<CompanyRankDto> findCompanyRank();
}
