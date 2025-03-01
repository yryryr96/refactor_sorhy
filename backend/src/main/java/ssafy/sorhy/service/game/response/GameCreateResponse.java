package ssafy.sorhy.service.game.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.game.Game;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.game.GameType;

@Getter
public class GameCreateResponse {

    private Long id;
    private GameType gameType;
    private GameTitle gameTitle;

    @Builder
    private GameCreateResponse(Long id, GameType gameType, GameTitle gameTitle) {
        this.id = id;
        this.gameType = gameType;
        this.gameTitle = gameTitle;
    }

    public static GameCreateResponse from(Game game) {
        return GameCreateResponse.builder()
                .id(game.getId())
                .gameType(game.getGameType())
                .gameTitle(game.getGameTitle())
                .build();
    }
}
