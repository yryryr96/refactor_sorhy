package ssafy.sorhy.repository.article;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.domain.article.QArticle;
import ssafy.sorhy.domain.article.SearchCondition;
import ssafy.sorhy.domain.user.User;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static ssafy.sorhy.domain.article.QArticle.article;

@RequiredArgsConstructor
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Article> searchArticleByCondition(User user, SearchCondition condition, Category category, String keyword, Pageable pageable) {

        List<Article> content = queryFactory
                .selectFrom(article)
                .where(
                        categoryEq(category),
                        searchConditionEq(condition, keyword)
                )
                .orderBy(article.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .from(article)
                .where(
                        categoryEq(category),
                        companyEq(category, user),
                        searchConditionEq(condition, keyword)
                );


        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression companyEq(Category category, User user) {
        return Category.isCompany(category) ? article.user.company.eq(user.getCompany()) : null;
    }

    private BooleanExpression categoryEq(Category category) {
        return category != null ? article.category.eq(category) : null;
    }

    private BooleanExpression searchConditionEq(SearchCondition searchCondition, String keyword) {
        if (!hasText(keyword) || searchCondition == null) {
            return null;  // 검색어가 없거나 조건이 없으면 필터링 안 함
        }

        QArticle article = QArticle.article;

        return switch (searchCondition) {
            case TITLE -> article.title.containsIgnoreCase(keyword);
            case CONTENT -> article.content.containsIgnoreCase(keyword);
            case NICKNAME -> article.user.nickname.containsIgnoreCase(keyword);
            default -> null;
        };
    }
}
