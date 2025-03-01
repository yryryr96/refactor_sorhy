package ssafy.sorhy.service.article.request;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.article.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
