package ssafy.sorhy.service.article.request;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.domain.article.SearchCondition;

@Getter
public class ArticleSearchRequest {

    private final SearchCondition searchCondition;

    @Length(min = 2)
    private final String keyword;

    private final Category category;

    public ArticleSearchRequest(SearchCondition searchCondition, String keyword, Category category) {
        this.searchCondition = searchCondition;
        this.keyword = keyword;
        this.category = category;
    }
}
