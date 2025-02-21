package ssafy.sorhy.domain.ranking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
public class Ranking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private GameTitle gameTitle;
    private int score = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Ranking(User user, GameTitle gameTitle, int score) {
        this.gameTitle = gameTitle;
        this.user = user;
        this.score = score;
    }

    public void updateRankingScore(int score) {
        this.score = score;
    }
}
