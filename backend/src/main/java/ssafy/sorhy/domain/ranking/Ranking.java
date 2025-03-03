package ssafy.sorhy.domain.ranking;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.user.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ranking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private GameTitle gameTitle;
    private int score = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Ranking(Long id, GameTitle gameTitle, int score, User user) {
        this.id = id;
        this.gameTitle = gameTitle;
        this.score = score;
        this.user = user;
    }

    public static Ranking of(User user, GameTitle gameTitle, int score) {
        return Ranking.builder()
                .user(user)
                .gameTitle(gameTitle)
                .score(score)
                .build();
    }

    public void updateRankingScore(int score) {
        this.score = score;
    }
}
