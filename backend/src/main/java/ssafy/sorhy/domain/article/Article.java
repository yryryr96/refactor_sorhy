package ssafy.sorhy.domain.article;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.dto.article.ArticleDto;
import ssafy.sorhy.dto.comment.CommentDto2;
import ssafy.sorhy.service.article.request.ArticleCreateRequest;
import ssafy.sorhy.service.article.request.ArticleUpdateRequest;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    private Article(Long id,
                   String title,
                   String content,
                   int viewCount,
                   Category category,
                   String imgUrl,
                   User user,
                   List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.category = category;
        this.imgUrl = imgUrl;
        this.user = user;
        this.comments = comments;
    }

    public void update(ArticleUpdateRequest dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.category = dto.getCategory();
    }

    public void addViewCount() {
        this.viewCount += 1;
    }

    public boolean isWrittenBy(User user) {
        return this.user.equals(user);
    }

    public static Article from(ArticleCreateRequest request, User user, String imgUrl) {
        return Article.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .imgUrl(imgUrl)
                .build();
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