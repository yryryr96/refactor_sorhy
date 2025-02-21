package ssafy.sorhy.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.dto.comment.CommentDto;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @Builder.Default
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public CommentDto.basicRes toBasicRes() {

        return CommentDto.basicRes.builder()
                .commentId(this.id)
                .nickname(this.user.getNickname())
                .content(this.content)
                .createdAt(this.createdAt)
                .build();
    }

    public void update(CommentDto.saveReq request) {

        this.content = request.getContent();
    }
}
