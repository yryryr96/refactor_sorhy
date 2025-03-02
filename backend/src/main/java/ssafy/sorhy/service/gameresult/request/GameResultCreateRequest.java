package ssafy.sorhy.service.gameresult.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import ssafy.sorhy.domain.gameresult.Team;

@Getter
public class GameResultCreateRequest {

    @NotNull
    private final Long gameId;
    @Max(10000)
    @Min(0)
    private final int score;
    @NotNull
    private final Team team;
    @NotNull(message = "캐릭터 아이디를 입력해주세요.")
    private final Long characterId;
    @NotNull(message = "승패를 입력해주세요.")
    private final boolean isWin;

    public GameResultCreateRequest(Long gameId, int score, Team team, Long characterId, boolean isWin) {
        this.gameId = gameId;
        this.score = score;
        this.team = team;
        this.characterId = characterId;
        this.isWin = isWin;
    }
}
