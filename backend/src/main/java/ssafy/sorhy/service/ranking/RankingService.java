package ssafy.sorhy.service.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.ranking.Ranking;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.dto.character.CharacterDto;
import ssafy.sorhy.service.ranking.dto.CompanyRankDto;
import ssafy.sorhy.repository.ranking.RankingRepository;
import ssafy.sorhy.repository.usercharacter.UserCharacterRepository;
import ssafy.sorhy.service.ranking.dto.RankingDto;
import ssafy.sorhy.service.ranking.response.EachGameRankingResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;
    private final UserCharacterRepository userCharacterRepository;

    public void save(User user, GameTitle gameTitle, int score) {
        Ranking ranking = Ranking.of(user, gameTitle, score);
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

    public List<CompanyRankDto> getCompanyRanking() {
        return new ArrayList<>(rankingRepository.findCompanyRank().stream()
                .collect(
                        Collectors.toMap(
                                CompanyRankDto::getCompanyName,
                                dto -> dto,
                                (existing, replacement) -> existing
                        )
                ).values()).stream()
                .sorted(Comparator.comparing(CompanyRankDto::getCompanyScore).reversed())
                .collect(Collectors.toList());
    }

    public EachGameRankingResponse getEachGameRanking(GameTitle gameTitle, Pageable pageable) {
        Page<Ranking> rankings = rankingRepository.findRankingByGameTitleOrderByDesc(gameTitle, pageable);
        List<RankingDto> result = rankings.getContent().stream()
                .map(ranking -> RankingDto.of(ranking, getMostUseCharacters(ranking.getUser()))
                ).collect(Collectors.toList());

        return EachGameRankingResponse.of(result);
    }

    private List<CharacterDto> getMostUseCharacters(User user) {
        return userCharacterRepository.findMostUse3CharactersByUserId(user.getId(),
                        PageRequest.of(0, 3))
                .stream()
                .map(uc -> CharacterDto.from(uc.getUseCount(), uc.getCharacter()))
                .collect(Collectors.toList());
    }
}
