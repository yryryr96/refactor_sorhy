package ssafy.sorhy.service.article.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.article.Category;

@Getter
public class ArticleUpdateRequest {

    @NotBlank(message = "제목을 입력하세요.")
    private final String title;

    @NotBlank(message = "내용을 입력하세요.")
    private final String content;

    @NotNull
    private final Category category;

    @Builder
    private ArticleUpdateRequest(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
