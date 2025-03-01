package ssafy.sorhy.service.article.response;

import lombok.Getter;

@Getter
public class ArticleUpdateResponse {

    private final String message;

    private ArticleUpdateResponse(String message) {
        this.message = message;
    }

    public static ArticleUpdateResponse of(String message) {
        return new ArticleUpdateResponse(message);
    }
}
