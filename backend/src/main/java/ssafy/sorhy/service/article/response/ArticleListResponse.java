package ssafy.sorhy.service.article.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.service.article.dto.ArticleDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArticleListResponse {

    private final int totalPage;
    private final long totalElement;
    private final List<ArticleDto> articles;

    @Builder
    private ArticleListResponse(int totalPage, long totalElement, List<ArticleDto> articles) {
        this.totalPage = totalPage;
        this.totalElement = totalElement;
        this.articles = articles;
    }

    public static ArticleListResponse from(Page<Article> articles) {

        return ArticleListResponse.builder()
                .totalPage(articles.getTotalPages())
                .totalElement(articles.getTotalElements())
                .articles(articles.getContent().stream()
                        .map(ArticleDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
