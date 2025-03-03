package ssafy.sorhy.domain.game;

public enum GameType {
    SINGLE("싱글 모드"),
    MULTI("멀티 모드");

    private final String description;

    GameType(String description) {
        this.description = description;
    }
}
