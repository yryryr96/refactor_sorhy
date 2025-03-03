package ssafy.sorhy.domain.gameresult;

public enum Team {
    RED("레드 팀"),
    BLUE("블루 팀");

    private final String description;

    Team(String description) {
        this.description = description;
    }
}
