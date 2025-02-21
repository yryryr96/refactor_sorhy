package ssafy.sorhy.dto.user;

import lombok.Getter;
import ssafy.sorhy.domain.game.GameTitle;

@Getter
public class UserEachGameScore {

    private final String gameTitle;
    private final int score;

    public UserEachGameScore(GameTitle gameTitle, int score) {
        this.gameTitle = gameTitle.toString();
        this.score = score;
    }
}
