package ssafy.sorhy.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class UserScore {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_score_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match matches;

    private int score;
}
