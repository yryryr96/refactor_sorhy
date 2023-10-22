package ssafy.sorhy.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.dto.user.UserDto;
import ssafy.sorhy.entity.gameresult.GameResult;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;

    @Builder.Default
    private int totalScore = 0;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<GameResult> gameResults = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public void updateTotalScore(int score) {
        this.totalScore += score;
    }

    public void addGameResult(GameResult gameResult) {
        gameResults.add(gameResult);
    }
    public void addArticle(Article article) {articles.add(article);}

    public UserDto.joinRes toJoinDto() {

        return UserDto.joinRes.builder()
                .nickname(this.nickname)
                .totalScore(this.totalScore)
                .build();
    }

    public UserDto.findRes toFindDto() {

        return UserDto.findRes.builder()
                .nickname(this.nickname)
                .totalScore(this.totalScore)
                .gameResults(this.gameResults)
                .build();
    }

}
