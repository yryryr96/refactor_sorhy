package ssafy.sorhy.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.sorhy.dto.ranking.CompanyRankDto;
import ssafy.sorhy.dto.ranking.RankingDto;
import ssafy.sorhy.entity.company.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByOrderByCompanyScoreDesc();

    @Query("select new ssafy.sorhy.dto.ranking.CompanyRankDto(c.companyName, u.nickname, u.totalScore) from User u " +
            "join u.company c " +
            "where (u.totalScore, c.id) in (select max(u2.totalScore), c2.id from User u2 join u2.company c2 group by c2.id) " +
            "order by c.companyScore")
    List<CompanyRankDto> findCompanyTopRankUser();
}
