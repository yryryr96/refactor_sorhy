package ssafy.sorhy.domain.user;

import lombok.*;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.service.user.request.UserCreateRequest;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.company.Company;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.domain.usercharacter.UserCharacter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String nickname;
    private String password;

    private int win;
    private int lose;
    private float winPercentage;
    private int totalScore;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles;

    @OneToMany(mappedBy = "user")
    private List<UserCharacter> characters;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<GameResult> gameResults = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    private User(Long id,
                String nickname,
                String password,
                int win,
                int lose,
                float winPercentage,
                int totalScore,
                List<Article> articles,
                List<UserCharacter> characters,
                List<Comment> comments,
                List<GameResult> gameResults,
                Company company) {

        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.win = win;
        this.lose = lose;
        this.winPercentage = winPercentage;
        this.totalScore = totalScore;
        this.articles = articles;
        this.characters = characters;
        this.comments = comments;
        this.gameResults = gameResults;
        this.company = company;
    }

    public static User of(String nickname,
                          String password,
                          int win,
                          int lose,
                          float winPercentage,
                          int totalScore,
                          List<Article> articles,
                          List<UserCharacter> characters,
                          List<Comment> comments,
                          List<GameResult> gameResults,
                          Company company) {

        return User.builder()
                .nickname(nickname)
                .password(password)
                .win(win)
                .lose(lose)
                .winPercentage(winPercentage)
                .totalScore(totalScore)
                .articles(articles)
                .characters(characters)
                .comments(comments)
                .gameResults(gameResults)
                .company(company)
                .build();
    }

    public static User create(UserCreateRequest request, Company company) {
        return User.builder()
                .password(request.getPassword())
                .nickname(request.getNickname())
                .company(company)
                .build();
    }

    public void updateScoreAndWinOrLose(int score, boolean isWin) {

        this.totalScore += score;
        this.company.updateCompanyScore(score);

        if (isWin) {
            this.win += 1;
        } else {
            this.lose += 1;
        }

        if (this.win != 0 || this.lose != 0) {
            this.winPercentage = ((float) this.win / (this.win + this.lose)) * 100;
        }
    }

    public void changeToEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public String getCompanyName() {
        return this.company.getCompanyName();
    }
}
