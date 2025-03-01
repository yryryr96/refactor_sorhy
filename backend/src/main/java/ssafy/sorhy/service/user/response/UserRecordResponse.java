package ssafy.sorhy.service.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.dto.character.CharacterDto;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.dto.user.UserDto;
import ssafy.sorhy.dto.user.UserRankInfoDto;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserRecordResponse {

    private String nickname;
    private int totalScore;
    private String companyName;
    private int win;
    private int lose;
    private float winPercentage;
    private Long personalRanking;
    private double rankPercent;
    private List<CharacterDto> top3Characters;
    private GameResultDto.searchGameRecordRes gameResults;

    @Builder
    public UserRecordResponse(String nickname,
                              int totalScore,
                              String companyName,
                              int win,
                              int lose,
                              float winPercentage,
                              Long personalRanking,
                              double rankPercent,
                              List<CharacterDto> top3Characters,
                              GameResultDto.searchGameRecordRes gameResults) {
        this.nickname = nickname;
        this.totalScore = totalScore;
        this.companyName = companyName;
        this.win = win;
        this.lose = lose;
        this.winPercentage = winPercentage;
        this.personalRanking = personalRanking;
        this.rankPercent = rankPercent;
        this.top3Characters = top3Characters;
        this.gameResults = gameResults;
    }

    public static UserRecordResponse of(User user,
                                        UserRankInfoDto userRankInfo,
                                        List<CharacterDto> mostUse3Characters,
                                        GameResultDto.searchGameRecordRes gameResults) {
        return UserRecordResponse.builder()
                .nickname(user.getNickname())
                .totalScore(user.getTotalScore())
                .companyName(user.getCompanyName())
                .win(user.getWin())
                .lose(user.getLose())
                .winPercentage(user.getWinPercentage())
                .personalRanking(userRankInfo.getPersonalRank())
                .top3Characters(mostUse3Characters)
                .gameResults(gameResults)
                .build();
    }
}
