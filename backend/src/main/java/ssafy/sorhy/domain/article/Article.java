package ssafy.sorhy.domain.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.dto.article.ArticleDto;
import ssafy.sorhy.dto.comment.CommentDto2;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;
    private String content;
    private int viewCount;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Nullable
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void update(ArticleDto.saveReq dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void addViewCount() {

        this.viewCount += 1;
    }

    public ArticleDto.basicRes toBasicRes() {

        return ArticleDto.basicRes.builder()
                .articleId(this.id)
                .nickname(this.user.getNickname())
                .title(this.title)
                .viewCount(this.viewCount)
                .imageUrl(this.imgUrl)
                .build();
    }

    public ArticleDto.detailRes toDetailRes(CommentDto2.pagingRes comments) {

        return ArticleDto.detailRes.builder()
                .articleId(this.id)
                .nickname(this.user.getNickname())
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .viewCount(this.viewCount)
                .imgUrl(this.imgUrl)
                .comments(comments)
                .build();
    }
}