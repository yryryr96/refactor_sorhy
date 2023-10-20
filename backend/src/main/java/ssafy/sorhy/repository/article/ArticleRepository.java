package ssafy.sorhy.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.sorhy.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
