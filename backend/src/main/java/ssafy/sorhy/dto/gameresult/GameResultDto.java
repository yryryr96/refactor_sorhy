package ssafy.sorhy.dto.gameresult;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.entity.UserTeam;
import ssafy.sorhy.entity.game.Game;
import ssafy.sorhy.entity.gameresult.GameResult;
import ssafy.sorhy.repository.game.GameRepository;

@Data
@RequiredArgsConstructor
public class GameResultDto {

    @Data
    public static class saveReq {

        private Long gameId;
        private Long userId;
        private int score;
        private String team;
        private Long characterId;
    }

    @Data
    @Builder
    public static class saveRes {

        private Long gameId;
        private Long userId;
        private int score;
    }
}
