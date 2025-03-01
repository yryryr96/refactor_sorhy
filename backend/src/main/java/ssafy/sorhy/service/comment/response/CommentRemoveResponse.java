package ssafy.sorhy.service.comment.response;

import lombok.Getter;

@Getter
public class CommentRemoveResponse {

    private final String message;

    private CommentRemoveResponse(String message) {
        this.message = message;
    }

    public static CommentRemoveResponse of(String message) {
        return new CommentRemoveResponse(message);
    }
}
