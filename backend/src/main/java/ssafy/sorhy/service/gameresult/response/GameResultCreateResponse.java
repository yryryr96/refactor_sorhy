package ssafy.sorhy.service.gameresult.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.domain.gameresult.Team;
import ssafy.sorhy.dto.gameresult.GameResultDto;

@Getter
public class GameResultCreateResponse {

    private Long gameId;
    private int score;
    private Team team;
    private Long characterId;
    private boolean isWin;

    @Builder
    private GameResultCreateResponse(Long gameId, int score, Team team, Long characterId, boolean isWin) {
        this.gameId = gameId;
        this.score = score;
        this.team = team;
        this.characterId = characterId;
        this.isWin = isWin;
    }


    public static GameResultCreateResponse from(GameResult gameResult) {
        return GameResultCreateResponse.builder()
                .gameId(gameResult.getId())
                .score(gameResult.getScore())
                .characterId(gameResult.getCharacterId())
                .isWin(gameResult.isWin())
                .team(gameResult.getTeam())
                .build();
    }
}
