package ssafy.sorhy.domain.game;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.service.game.request.GameCreateRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private GameTitle gameTitle;

    @OneToMany(mappedBy = "game")
    private List<GameResult> gameResults = new ArrayList<>();

    @Builder
    private Game(Long id, GameType gameType, GameTitle gameTitle, List<GameResult> gameResults) {
        this.id = id;
        this.gameType = gameType;
        this.gameTitle = gameTitle;
        this.gameResults = gameResults;
    }

    public static Game from(GameCreateRequest request) {
        return Game.builder()
                .gameTitle(request.getGameTitle())
                .gameType(request.getGameType())
                .build();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Game game = (Game) object;
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameType, gameTitle, gameResults);
    }
}
