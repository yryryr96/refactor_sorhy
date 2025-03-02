package ssafy.sorhy.domain.comment;

import lombok.*;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.dto.comment.CommentDto2;
import ssafy.sorhy.service.comment.request.CommentCreateRequest;
import ssafy.sorhy.service.comment.request.CommentUpdateRequest;

import jakarta.persistence.*;

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

    public CommentDto2.basicRes toBasicRes() {

        return CommentDto2.basicRes.builder()
                .commentId(this.id)
                .nickname(this.user.getNickname())
                .content(this.content)
                .build();
    }

    public void update(CommentUpdateRequest request) {
        this.content = request.getContent();
    }
}
