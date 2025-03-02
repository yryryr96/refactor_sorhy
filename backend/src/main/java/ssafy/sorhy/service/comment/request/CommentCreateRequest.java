package ssafy.sorhy.service.comment.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {

    @NotBlank
    private String content;

    public CommentCreateRequest(String content) {
        this.content = content;
    }
}
