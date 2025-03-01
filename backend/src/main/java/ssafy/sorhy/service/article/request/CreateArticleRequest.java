package ssafy.sorhy.service.article.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.article.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateArticleRequest {

    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    @NotNull
    private Category category;

    public CreateArticleRequest(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
