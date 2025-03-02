package ssafy.sorhy.service.comment.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {

    @NotBlank
    private String content;

    public CommentCreateRequest(String content) {
        this.content = content;
    }
}
