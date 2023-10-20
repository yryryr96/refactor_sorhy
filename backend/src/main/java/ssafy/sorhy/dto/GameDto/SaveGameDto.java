package ssafy.sorhy.dto.GameDto;

import lombok.Builder;
import lombok.Data;
import ssafy.sorhy.entity.Game;
import ssafy.sorhy.entity.GameTitle;
import ssafy.sorhy.entity.GameType;

@Data
public class SaveGameDto {

    @Data
    public static class Request {
        private String gameType;
        private String gameTitle;

        public Game toGameEntity() {
            GameType gameType = GameType.valueOf(this.gameType);
            GameTitle gameTitle = GameTitle.valueOf(this.gameTitle);
            return Game.builder()
                    .gameType(gameType)
                    .gameTitle(gameTitle)
                    .build();
        }
    }

    @Builder
    @Data
    public static class Response {
        private Long id;
        private GameType gameType;
        private GameTitle gameTitle;
    }
}
