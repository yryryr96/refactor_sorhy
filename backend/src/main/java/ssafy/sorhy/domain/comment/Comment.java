package ssafy.sorhy.domain.comment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.service.comment.request.CommentCreateRequest;
import ssafy.sorhy.service.comment.request.CommentUpdateRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    private Comment(Long id, String content, User user, Article article) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.article = article;
    }

    public static Comment from(CommentCreateRequest request, User user, Article article) {
        return Comment.builder()
                .content(request.getContent())
                .user(user)
                .article(article)
                .build();
    }

    public void update(CommentUpdateRequest request) {
        this.content = request.getContent();
    }
}
