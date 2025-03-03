package ssafy.sorhy.service.ranking.dto;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.ranking.Ranking;
import ssafy.sorhy.dto.character.CharacterDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RankingDto {

    private final String nickname;
    private final int score;
    private final LocalDateTime createdAt;
    private final String company;
    private final List<CharacterDto> mostUseCharacters;

    @Builder
    private RankingDto(String nickname, int score, LocalDateTime createdAt, String company, List<CharacterDto> mostUseCharacters) {
        this.nickname = nickname;
        this.score = score;
        this.createdAt = createdAt;
        this.company = company;
        this.mostUseCharacters = mostUseCharacters;
    }

    public static RankingDto of(Ranking ranking, List<CharacterDto> mostUseCharacters) {
        return RankingDto.builder()
                .nickname(ranking.getUser().getNickname())
                .score(ranking.getScore())
                .createdAt(ranking.getCreatedAt())
                .company(ranking.getUser().getCompanyName())
                .mostUseCharacters(mostUseCharacters)
                .build();
    }
}
