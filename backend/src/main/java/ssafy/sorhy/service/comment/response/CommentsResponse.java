package ssafy.sorhy.service.comment.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.service.comment.dto.CommentDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentsResponse {

    private final long totalElement;
    private final int totalPage;
    private final List<CommentDto> comments;

    @Builder
    private CommentsResponse(long totalElement, int totalPage, List<CommentDto> comments) {
        this.totalElement = totalElement;
        this.totalPage = totalPage;
        this.comments = comments;
    }

    public static CommentsResponse of(Page<Comment> result) {
        return CommentsResponse.builder()
                .totalElement(result.getTotalElements())
                .totalPage(result.getTotalPages())
                .comments(
                        result.stream()
                                .map(CommentDto::from)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
