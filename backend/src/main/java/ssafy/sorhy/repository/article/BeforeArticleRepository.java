package ssafy.sorhy.repository.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.article.Category;

public interface BeforeArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a where a.category = :category order by a.id desc")
    Page<Article> findAllArticleOrderByIdDesc(Category category, Pageable pageable);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = 'COMPANY' and u.company.id = :companyId " +
            "order by a.id desc")
    Page<Article> findCompanyArticlesOrderByDesc(Long companyId, Pageable pageable);

    @Query("select a from Article a where a.category = :category order by a.viewCount desc")
    Page<Article> findHotArticles(Category category, Pageable pageable);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = 'COMPANY' and u.company.id = :companyId " +
            "order by a.viewCount desc")
    Page<Article> findCompanyHotArticles(Long companyId, Pageable pageable);

    @Query("select a from Article a where a.category != 'COMPANY' and a.viewCount != 0 order by a.viewCount desc")
    Page<Article> findCurrentIssueArticles(Pageable pageable);

    @Query("select count(a) from Article a where a.user.nickname = :nickname")
    Long countArticleByNickname(String nickname);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = :category and a.title like %:word% " +
            "order by a.id desc")
    Page<Article> searchArticleByTitle(String word, Category category, Pageable pageable);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = :category and u.nickname = :nickname " +
            "order by a.id desc")
    Page<Article> searchArticleByNickname(String nickname, Category category, Pageable pageable);

    @Query("select a from Article a " +
            "where a.category = :category and a.content like %:word% " +
            "order by a.id desc")
    Page<Article> searchArticleByContent(String word, Category category, Pageable pageable);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = :category and a.title like %:word% or a.content like %:word% " +
            "order by a.id desc")
    Page<Article> searchArticleByTitleAndContent(String word, Category category, Pageable pageable);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = 'COMPANY' and u.company.id = :companyId and a.title like %:word% " +
            "order by a.id desc")
    Page<Article> searchCompanyArticleByTitle(String word, Long companyId, Pageable pageable);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = 'COMPANY' and u.nickname = :nickname " +
            "order by a.id desc")
    Page<Article> searchCompanyArticleByNickname(String nickname, Pageable pageable);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = 'COMPANY' and u.company.id = :companyId and a.content like %:word% " +
            "order by a.id desc")
    Page<Article> searchCompanyArticleByContent(String word, Long companyId, Pageable pageable);

    @Query("select a from Article a " +
            "join a.user u " +
            "where a.category = 'COMPANY' and u.company.id = :companyId and a.title like %:word% or a.content like %:word% " +
            "order by a.id desc")
    Page<Article> searchCompanyArticleByTitleAndContent(String word, Long companyId, Pageable pageable);
}
