package ssafy.sorhy.service.comment.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequest {

    private String content;

    public CommentUpdateRequest(String content) {
        this.content = content;
    }
}
