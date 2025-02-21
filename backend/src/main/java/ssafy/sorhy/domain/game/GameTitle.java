package ssafy.sorhy.domain.game;


public enum GameTitle {
    BALLOON("풍선 게임"),
    SWIM("수영 게임"),
    HOUSE("집 짓기 게임")
    ;

    private final String description;

    GameTitle(String description) {
        this.description = description;
    }
}
