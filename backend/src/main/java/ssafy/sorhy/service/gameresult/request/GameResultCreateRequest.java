package ssafy.sorhy.service.gameresult.request;

import lombok.Getter;
import ssafy.sorhy.domain.gameresult.Team;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class GameResultCreateRequest {

    @NotBlank
    private Long gameId;
    @NotBlank
    private int score;
    @NotNull
    private Team team;
    @NotBlank(message = "캐릭터 아이디를 입력해주세요.")
    private Long characterId;
    @NotBlank(message = "승패를 입력해주세요.")
    private boolean isWin;

    public GameResultCreateRequest(Long gameId, int score, Team team, Long characterId, boolean isWin) {
        this.gameId = gameId;
        this.score = score;
        this.team = team;
        this.characterId = characterId;
        this.isWin = isWin;
    }
}
