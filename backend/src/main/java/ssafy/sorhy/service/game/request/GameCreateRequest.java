package ssafy.sorhy.service.game.request;

import lombok.Getter;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.game.GameType;

import javax.validation.constraints.NotBlank;

@Getter
public class GameCreateRequest {

    @NotBlank
    private GameType gameType;
    @NotBlank
    private GameTitle gameTitle;
}
