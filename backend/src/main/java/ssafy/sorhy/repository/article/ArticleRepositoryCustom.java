package ssafy.sorhy.repository.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.domain.article.SearchCondition;
import ssafy.sorhy.domain.user.User;

public interface ArticleRepositoryCustom {

    Page<Article> searchArticleByCondition(User user, SearchCondition condition, Category category, String keyword, Pageable pageable);

    Page<Article> getAllArticlesByCategory(User user, Category category, Pageable pageable);

    Page<Article> getHotArticles(User user, Category category, Pageable pageable);

    Page<Article> getCurrentIssueArticles(Pageable pageable);

    Long countByNickname(String nickname);
}
