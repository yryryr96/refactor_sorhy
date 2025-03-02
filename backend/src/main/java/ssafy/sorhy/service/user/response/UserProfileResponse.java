package ssafy.sorhy.service.user.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.dto.character.CharacterDto;

import java.util.List;

@Getter
public class UserProfileResponse {

    private String nickname;
    private int totalScore;
    private Long articleCount;
    private Long commentCount;
    private String companyName;
    private int win;
    private int lose;
    private float winPercentage;
    private List<CharacterDto> top3Characters;

    @Builder
    private UserProfileResponse(String nickname,
                               int totalScore,
                               Long articleCount,
                               Long commentCount,
                               String companyName,
                               int win,
                               int lose,
                               float winPercentage,
                               List<CharacterDto> top3Characters) {
        this.nickname = nickname;
        this.totalScore = totalScore;
        this.articleCount = articleCount;
        this.commentCount = commentCount;
        this.companyName = companyName;
        this.win = win;
        this.lose = lose;
        this.winPercentage = winPercentage;
        this.top3Characters = top3Characters;
    }

    public static UserProfileResponse of(User user, Long articleCount, Long commentCount, List<CharacterDto> top3Characters) {
        return UserProfileResponse.builder()
                .nickname(user.getNickname())
                .totalScore(user.getTotalScore())
                .articleCount(articleCount)
                .commentCount(commentCount)
                .companyName(user.getCompanyName())
                .win(user.getWin())
                .lose(user.getLose())
                .winPercentage(user.getWinPercentage())
                .top3Characters(top3Characters)
                .build();
    }
}
