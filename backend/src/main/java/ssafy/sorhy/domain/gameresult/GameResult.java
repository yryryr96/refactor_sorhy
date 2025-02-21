package ssafy.sorhy.domain.gameresult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.domain.game.Game;
import ssafy.sorhy.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GameResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameResult_id")
    private Long id;

    private int score;
    private Long characterId;
    private boolean winner;

    @Enumerated(EnumType.STRING)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public GameResultDto.saveRes toSaveResDto(GameResult gameResult) {

        return GameResultDto.saveRes.builder()
                .gameId(this.getGame().getId())
                .score(this.score)
                .characterId(this.characterId)
                .winner(this.winner)
                .team(this.team)
                .build();
    }
}
