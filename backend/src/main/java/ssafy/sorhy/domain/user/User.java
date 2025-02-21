package ssafy.sorhy.domain.user;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.dto.user.UserDto;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.company.Company;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.domain.usercharacter.UserCharacter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    @OneToMany(mappedBy = "user")
    private List<UserCharacter> characters;

    @OneToMany(mappedBy = "user")
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
                .build();
    }

    public void updateScoreAndWinOrLose(int score, boolean winner) {

        this.totalScore += score;
        this.company.updateCompanyScore(score);

        if (winner) {
            this.win += 1;
        } else {
            this.lose += 1;
        }

        if (this.win != 0 || this.lose != 0) {
            this.winPercentage = ((float) this.win / (this.win + this.lose)) * 100;
        }
    }

    public User hashPassword(BCryptPasswordEncoder encoder) {
        this.password = encoder.encode(this.password);
        return this;
    }

    public UserDto.joinRes toJoinDto() {

        return UserDto.joinRes.builder()
                .nickname(this.nickname)
                .build();
    }

    public UserDto.recordRes toRecordRes(List<UserDto.top3Character> top3Characters,
                                         GameResultDto.searchGameRecordRes gameResults,
                                         Long personalRanking,
                                         double rankPercent) {

        return UserDto.recordRes.builder()
                .nickname(this.nickname)
                .totalScore(this.totalScore)
                .companyName(this.company.getCompanyName())
                .win(this.win)
                .lose(this.lose)
                .winPercentage(this.winPercentage)
                .personalRanking(personalRanking)
                .rankPercent(rankPercent)
                .top3Characters(top3Characters)
                .gameResults(gameResults)
                .build();
    }

    public UserDto.profileRes toProfileDto(Long articleCount,
                                           Long commentCount,
                                           List<UserDto.top3Character> top3CharacterList) {

        return UserDto.profileRes.builder()
                .nickname(this.nickname)
                .articleCount(articleCount)
                .commentCount(commentCount)
                .companyName(this.company.getCompanyName())
                .totalScore(this.totalScore)
                .win(this.win)
                .lose(this.lose)
                .winPercentage(this.winPercentage)
                .top3Characters(top3CharacterList)
                .build();
    }
}
