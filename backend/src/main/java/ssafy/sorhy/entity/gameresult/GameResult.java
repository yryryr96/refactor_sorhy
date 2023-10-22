package ssafy.sorhy.entity.gameresult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.entity.UserTeam;
import ssafy.sorhy.entity.game.Game;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GameResult {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameResult_id")
    private Long id;

    private int score;
    private Long characterId;

    @Enumerated(EnumType.STRING)
    private UserTeam userTeam;

    @Builder.Default
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public GameResultDto.saveRes toSaveResDto(GameResult gameResult) {

        return GameResultDto.saveRes.builder()
                .gameId(this.getGame().getId())
                .userId(this.getUser().getId())
                .score(this.score)
                .build();
    }
}
