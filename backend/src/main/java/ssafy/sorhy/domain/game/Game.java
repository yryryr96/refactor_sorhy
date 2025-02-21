package ssafy.sorhy.domain.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.dto.game.GameDto;
import ssafy.sorhy.domain.gameresult.GameResult;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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
    @Builder.Default
    private List<GameResult> gameResults = new ArrayList<>();

    public GameDto.Response toSaveGameDto() {
        return GameDto.Response.builder()
                .id(this.id)
                .gameTitle(this.gameTitle)
                .gameType(this.gameType)
                .build();
    }
}
