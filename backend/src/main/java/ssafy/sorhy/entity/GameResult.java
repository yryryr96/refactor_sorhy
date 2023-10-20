package ssafy.sorhy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

}
