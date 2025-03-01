package ssafy.sorhy.service.article.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.article.Article;

import java.time.LocalDateTime;

@Getter
public class CreateArticleResponse {

    private final Long articleId;
    private final String nickname;
    private final String title;
    private final int viewCount;
    private final String imageUrl;
    private final LocalDateTime createdAt;

    @Builder
    private CreateArticleResponse(Long articleId, String nickname, String title, int viewCount, String imageUrl, LocalDateTime createdAt) {
        this.articleId = articleId;
        this.nickname = nickname;
        this.title = title;
        this.viewCount = viewCount;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    public static CreateArticleResponse from(Article article) {
        return CreateArticleResponse.builder()
                .articleId(article.getId())
                .nickname(article.getUser().getNickname())
                .title(article.getTitle())
                .viewCount(article.getViewCount())
                .imageUrl(article.getImgUrl())
                .createdAt(article.getCreatedAt())
                .build();
    }
}
