package ssafy.sorhy.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.domain.company.Company;
import ssafy.sorhy.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    public static class loginReq {

        @NotBlank
        private String nickname;
        @NotBlank
        private String password;
    }

    @Data
    @Builder
    public static class loginRes {

        @NotBlank
        private String token;
        @NotBlank
        private Long companyId;
        @NotBlank
        private String nickname;
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
