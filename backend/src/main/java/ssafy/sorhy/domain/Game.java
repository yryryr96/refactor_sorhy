package ssafy.sorhy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Game {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;

    private GameType gameType;
    private GameTitle gameTitle;

    @OneToMany(mappedBy = "game")
    @Builder.Default
    private List<Match> matches = new ArrayList<>();
}
