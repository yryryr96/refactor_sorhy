package ssafy.sorhy.domain.gameresult;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.game.Game;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.service.gameresult.request.GameResultCreateRequest;
import ssafy.sorhy.util.converter.BooleanToYNConverter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameResult_id")
    private Long id;

    private int score;
    private Long characterId;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean isWin;

    @Enumerated(EnumType.STRING)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Builder
    private GameResult(Long id, int score, Long characterId, boolean isWin, Team team, User user, Game game) {
        this.id = id;
        this.score = score;
        this.characterId = characterId;
        this.isWin = isWin;
        this.team = team;
        this.user = user;
        this.game = game;
    }

    public static GameResult from(User user, Game game, GameResultCreateRequest request) {
        return GameResult.builder()
                .game(game)
                .user(user)
                .score(request.getScore())
                .characterId(request.getCharacterId())
                .isWin(request.isWin())
                .team(request.getTeam())
                .build();
    }
}
