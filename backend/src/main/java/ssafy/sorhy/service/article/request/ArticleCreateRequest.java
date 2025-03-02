package ssafy.sorhy.service.article.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.article.Category;

@Getter
@NoArgsConstructor
public class ArticleCreateRequest {

    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    @NotNull
    private Category category;

    public ArticleCreateRequest(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
