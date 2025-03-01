package ssafy.sorhy.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.BaseEntity;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.dto.comment.CommentDto2;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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

    public CommentDto2.basicRes toBasicRes() {

        return CommentDto2.basicRes.builder()
                .commentId(this.id)
                .nickname(this.user.getNickname())
                .content(this.content)
                .build();
    }

    public void update(CommentDto2.saveReq request) {
        this.content = request.getContent();
    }
}
