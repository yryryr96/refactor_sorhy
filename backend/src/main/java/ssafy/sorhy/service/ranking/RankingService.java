package ssafy.sorhy.service.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.dto.ranking.CompanyRankDto;
import ssafy.sorhy.dto.ranking.RankingDto;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.ranking.Ranking;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.repository.company.CompanyRepository;
import ssafy.sorhy.repository.ranking.RankingRepository;
import ssafy.sorhy.service.usercharacter.UserCharacterService;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;
    private final CompanyRepository companyRepository;
    private final UserCharacterService userCharacterService;

    public void save(User user, GameTitle gameTitle, int score) {

        Ranking ranking = new Ranking(user, gameTitle, score);
        rankingRepository.save(ranking);
    }

    public void updateRanking(User user, GameTitle gameTitle, int score) {

        Ranking ranking = rankingRepository.findByUserAndGameTitle(user, gameTitle).orElse(null);

        if (ranking == null) {
            save(user, gameTitle, score);
            return;
        }

        if (ranking.getScore() < score) {

            ranking.updateRankingScore(score);
        }
    }

    public List<CompanyRankDto> companyRank() {

        return companyRepository.findCompanyTopRankUser();
    }

    public List<RankingDto.personalRankRes> eachGameRank(String gameTitle, Pageable pageable) {

        GameTitle title = GameTitle.valueOf(gameTitle);
        return rankingRepository.findByGameTitleOrderByDesc(title, pageable).stream()
                .map(ranking -> new RankingDto.personalRankRes(ranking,
                        userCharacterService.findTop3Characters(ranking.getUser().getId())))
                .collect(Collectors.toList());
    }
}
