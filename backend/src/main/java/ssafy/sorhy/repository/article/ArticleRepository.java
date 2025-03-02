package ssafy.sorhy.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.sorhy.domain.article.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

}
