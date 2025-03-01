package ssafy.sorhy.service.comment.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.user.User;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponse {

    private final Long commentId;
    private final String nickname;
    private final String content;
    private final LocalDateTime createdAt;

    @Builder
    private CommentCreateResponse(Long commentId, String nickname, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentCreateResponse from(Comment comment, User user) {
        return CommentCreateResponse.builder()
                .commentId(comment.getId())
                .nickname(user.getNickname())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
