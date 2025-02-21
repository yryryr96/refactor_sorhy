package ssafy.sorhy.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssafy.sorhy.dto.gameresult.GameResultDto;

import java.util.List;

@Data
public class UserDto {


    @Builder
    @Data
    public static class joinRes {

        private String nickname;
    }

    @Data
    @Builder
    public static class recordRes {

        private String nickname;
        private int totalScore;
        private String companyName;
        private int win;
        private int lose;
        private float winPercentage;
        private Long personalRanking;
        private double rankPercent;
        private List<top3Character> top3Characters;
        private GameResultDto.searchGameRecordRes gameResults;
    }

    @Data
    @Builder
    public static class profileRes {

        private String nickname;
        private int totalScore;
        private Long articleCount;
        private Long commentCount;
        private String companyName;
        private int win;
        private int lose;
        private float winPercentage;
        private List<top3Character> top3Characters;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class top3Character {

        private Long characterId;
        private String characterName;
        private Long cnt;
    }
}
