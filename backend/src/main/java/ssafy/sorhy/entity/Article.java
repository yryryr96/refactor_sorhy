package ssafy.sorhy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ssafy.sorhy.dto.article.ArticleDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;
    private String content;

    @Builder.Default
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    public ArticleDto.BasicRes toBasicRes() {

        return ArticleDto.BasicRes.builder()
                .articleId(this.id)
                .userId(this.user.getId())
                .title(this.title)
                .build();
    }

    public ArticleDto.DetailRes toDetailRes() {

        return ArticleDto.DetailRes.builder()
                .articleId(this.id)
                .nickname(this.user.getNickname())
                .title(this.title)
                .content(this.content)
                .createdAt(this.createdAt)
                .build();
    }
}
