package ssafy.sorhy.service.game.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.game.GameType;

@Getter
public class GameCreateRequest {

    @NotBlank
    private GameType gameType;
    @NotBlank
    private GameTitle gameTitle;
}
