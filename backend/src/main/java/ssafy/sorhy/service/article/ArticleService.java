package ssafy.sorhy.service.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.domain.article.SearchCondition;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.exception.ResourceNotFoundException;
import ssafy.sorhy.exception.UnAuthorizedException;
import ssafy.sorhy.repository.article.ArticleRepository;
import ssafy.sorhy.repository.comment.CommentRepository;
import ssafy.sorhy.repository.user.UserRepository;
import ssafy.sorhy.service.article.request.ArticleCreateRequest;
import ssafy.sorhy.service.article.request.ArticleSearchRequest;
import ssafy.sorhy.service.article.request.ArticleUpdateRequest;
import ssafy.sorhy.service.article.response.*;
import ssafy.sorhy.service.s3.S3UploadService;

import java.io.IOException;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final S3UploadService s3UploadService;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public ArticleCreateResponse create(String nickname,
                                        MultipartFile file,
                                        ArticleCreateRequest request) throws IOException {

        String imgUrl;
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new ResourceNotFoundException("User"));

        if (file != null) {
            imgUrl = s3UploadService.uploadFile(file);
        } else {
            imgUrl = null;
        }

        Article article = Article.from(request, user, imgUrl);
        Article savedArticle = articleRepository.save(article);

        return ArticleCreateResponse.from(savedArticle, user);
    }

    @Transactional
    public ArticleUpdateResponse update(Long articleId, String nickname, ArticleUpdateRequest request) {

        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article"));

        if (article.isWrittenBy(user)) {
            article.update(request);
            return ArticleUpdateResponse.of("게시글 수정 완료");
        }

        throw new UnAuthorizedException();
    }

    @Transactional
    public ArticleRemoveResponse remove(Long articleId, String nickname) {

        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article"));

        if (article.isWrittenBy(user)) {
            articleRepository.delete(article);
            return ArticleRemoveResponse.of("게시글 삭제 완료");
        }

        throw new UnAuthorizedException();
    }

    public ArticleListResponse getAllArticlesByCategory(String nickname, Category category, Pageable pageable) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Page<Article> articles = articleRepository.getAllArticlesByCategory(user, category, pageable);
        return ArticleListResponse.from(articles);
    }

    public ArticleListResponse getHotArticles(String nickname, Category category, Pageable pageable) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Page<Article> articles = articleRepository.getHotArticles(user, category, pageable);
        return ArticleListResponse.from(articles);
    }

    public ArticleListResponse getCurrentIssueArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.getCurrentIssueArticles(pageable);
        return ArticleListResponse.from(articles);
    }

    @Transactional
    public ArticleDetailResponse getArticleDetail(Long articleId, Pageable pageable) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article"));
        Page<Comment> comments = commentRepository.findByArticleIdOrderByIdDesc(articleId, pageable);

        // 트랜잭션 분리, 책임 분리 필요
        article.addViewCount();

        return ArticleDetailResponse.of(article, comments);
    }

    public ArticleListResponse getArticlesBySearchCondition(ArticleSearchRequest request, String nickname, Pageable pageable) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Page<Article> articles = getArticlesByArticleSearchRequest(request, user, pageable);
        return ArticleListResponse.from(articles);
    }

    private Page<Article> getArticlesByArticleSearchRequest(ArticleSearchRequest request, User user, Pageable pageable) {
        SearchCondition searchCondition = request.getSearchCondition();
        Category category = request.getCategory();
        String keyword = request.getKeyword();
        return articleRepository.searchArticleByCondition(user, searchCondition, category, keyword, pageable);
    }
}
