package ssafy.sorhy.service.comment.dto;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.comment.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentDto {

    private Long commentId;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    private CommentDto(Long commentId, String nickname, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getId())
                .nickname(comment.getUser().getNickname())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
