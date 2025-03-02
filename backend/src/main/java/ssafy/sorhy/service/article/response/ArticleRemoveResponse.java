package ssafy.sorhy.service.article.response;

import lombok.Getter;

@Getter
public class ArticleRemoveResponse {

    private final String message;

    private ArticleRemoveResponse(String message) {
        this.message = message;
    }

    public static ArticleRemoveResponse of(String message) {
        return new ArticleRemoveResponse(message);
    }
}
