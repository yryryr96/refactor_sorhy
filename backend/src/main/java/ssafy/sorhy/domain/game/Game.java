package ssafy.sorhy.domain.game;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.service.game.request.GameCreateRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
}
