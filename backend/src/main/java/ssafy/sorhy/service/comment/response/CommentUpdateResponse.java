package ssafy.sorhy.service.comment.response;

import lombok.Getter;
import ssafy.sorhy.service.comment.request.CommentUpdateRequest;

@Getter
public class CommentUpdateResponse {

    private final String message;

    private CommentUpdateResponse(String message) {
        this.message = message;
    }

    public static CommentUpdateResponse of(String message) {
        return new CommentUpdateResponse(message);
    }
}
