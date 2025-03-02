package ssafy.sorhy.service.article.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.service.comment.response.CommentsResponse;

import java.time.LocalDateTime;

@Getter
public class ArticleDetailResponse {

    private final Long articleId;
    private final String nickname;
    private final String title;
    private final String content;
    private final Category category;
    private final int viewCount;
    private final LocalDateTime createdAt;
    private final String imgUrl;
    private final CommentsResponse comments;

    @Builder
    private ArticleDetailResponse(Long articleId,
                                  String nickname,
                                  String title,
                                  String content,
                                  Category category,
                                  int viewCount,
                                  LocalDateTime createdAt,
                                  String imgUrl,
                                  CommentsResponse comments) {
        this.articleId = articleId;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.category = category;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.imgUrl = imgUrl;
        this.comments = comments;
    }

    public static ArticleDetailResponse of(Article article, Page<Comment> comments) {
        return ArticleDetailResponse.builder()
                .articleId(article.getId())
                .nickname(article.getUser().getNickname())
                .title(article.getTitle())
                .content(article.getContent())
                .category(article.getCategory())
                .viewCount(article.getViewCount())
                .createdAt(article.getCreatedAt())
                .imgUrl(article.getImgUrl())
                .comments(CommentsResponse.of(comments))
                .build();
    }
}
