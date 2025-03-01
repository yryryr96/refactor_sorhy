package ssafy.sorhy.service.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ssafy.sorhy.dto.article.ArticleDto;
import ssafy.sorhy.dto.comment.CommentDto2;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.domain.article.SearchCond;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.exception.CustomException;
import ssafy.sorhy.exception.ErrorCode;
import ssafy.sorhy.exception.ResourceNotFoundException;
import ssafy.sorhy.exception.UnAuthorizedException;
import ssafy.sorhy.repository.article.ArticleRepository;
import ssafy.sorhy.repository.comment.CommentRepository;
import ssafy.sorhy.repository.user.UserRepository;
import ssafy.sorhy.service.article.request.ArticleCreateRequest;
import ssafy.sorhy.service.article.request.ArticleUpdateRequest;
import ssafy.sorhy.service.article.response.ArticleListResponse;
import ssafy.sorhy.service.article.response.ArticleUpdateResponse;
import ssafy.sorhy.service.article.response.CreateArticleResponse;
import ssafy.sorhy.service.s3.S3UploadService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ssafy.sorhy.domain.article.SearchCond.valueOf;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final S3UploadService s3UploadService;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateArticleResponse create(String nickname,
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

        return CreateArticleResponse.from(savedArticle);
    }

    public ArticleListResponse getAllArticlesByCategory(Category category, Pageable pageable) {
        Page<Article> articles = articleRepository.findAllArticleOrderByIdDesc(category, pageable);
        return ArticleListResponse.from(articles);
    }

    public ArticleListResponse getAllCompanyArticles(String nickname, Pageable pageable) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Long companyId = user.getCompany().getId();
        Page<Article> articles = articleRepository.findCompanyArticlesOrderByDesc(companyId, pageable);
        return ArticleListResponse.from(articles);
    }

    public ArticleListResponse getCompanyHotArticles(String nickname, Pageable pageable) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Long companyId = user.getCompany().getId();
        Page<Article> articles = articleRepository.findCompanyHotArticles(companyId, pageable);
        return ArticleListResponse.from(articles);
    }

    public ArticleListResponse getHotArticles(Category category, Pageable pageable) {
        Page<Article> articles = articleRepository.findHotArticles(category, pageable);
        return ArticleListResponse.from(articles);
    }

    public ArticleListResponse getCurrentIssueArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findCurrentIssueArticles(pageable);
        return ArticleListResponse.from(articles);
    }

    public ArticleDto.detailRes findById(Long articleId, Pageable pageable) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        Page<Comment> result = commentRepository.findByArticleIdOrderByIdDesc(article.getId(), pageable);
        CommentDto2.pagingRes comments = CommentDto2.pagingRes.builder()
                .comments(result.stream()
                        .map(Comment::toBasicRes)
                        .collect(Collectors.toList()))
                .totalElement(result.getTotalElements())
                .totalPage(result.getTotalPages())
                .build();

        article.addViewCount();
        return article.toDetailRes(comments);
    }

    @Transactional
    public ArticleUpdateResponse update(Long articleId, String nickname, ArticleUpdateRequest request) {

        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article"));

        if (article.isUser(user)) {
            article.update(request);
            return ArticleUpdateResponse.of("게시글 수정 완료");
        } else {
            throw new UnAuthorizedException();
        }
    }

    public String delete(Long articleId, String nickname) {

        User user = findUser(nickname);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        if (article.getUser().equals(user)) {
            articleRepository.delete(article);
            return "delete success!!";
        } else {
            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
        }
    }

    public ArticleDto.pagingRes searchArticle(ArticleDto.searchReq request, String category, Pageable pageable) {

        String word = request.getWord();
        Category articleCategory;
        SearchCond searchCond;
        Page<Article> result = null;

        try {
            articleCategory = Category.valueOf(category);
            searchCond = valueOf(request.getSearchCond());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_VALID_REQUEST);
        }

        switch (searchCond) {
            case NONE:
                result = articleRepository.searchArticleByTitleAndContent(word, articleCategory, pageable);
                break;
            case TITLE:
                result = articleRepository.searchArticleByTitle(word, articleCategory, pageable);
                break;
            case NICKNAME:
                result = articleRepository.searchArticleByNickname(word, articleCategory, pageable);
                break;
            case CONTENT:
                result = articleRepository.searchArticleByContent(word, articleCategory, pageable);
                break;
            default:
                throw new CustomException(ErrorCode.NOT_VALID_REQUEST);
        }

        return toPagingRes(toDtoList(result.getContent()), result.getTotalElements(), result.getTotalPages());
    }

    public ArticleDto.pagingRes searchCompanyArticle(String nickname, ArticleDto.searchReq request, Pageable pageable) {

        String word = request.getWord();
        SearchCond searchCond;
        Page<Article> result = null;
        User user = findUser(nickname);
        Long companyId = user.getCompany().getId();

        try {
            searchCond = valueOf(request.getSearchCond());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_VALID_REQUEST);
        }

        switch (searchCond) {
            case NONE:
                result = articleRepository.searchCompanyArticleByTitleAndContent(word, companyId, pageable);
                break;
            case TITLE:
                result = articleRepository.searchCompanyArticleByTitle(word, companyId, pageable);
                break;
            case NICKNAME:
                result = articleRepository.searchCompanyArticleByNickname(word, pageable);
                break;
            case CONTENT:
                result = articleRepository.searchCompanyArticleByContent(word, companyId, pageable);
                break;
            default:
                throw new CustomException(ErrorCode.NOT_VALID_REQUEST);
        }
        return toPagingRes(toDtoList(result.getContent()), result.getTotalElements(), result.getTotalPages());
    }

    private List<ArticleDto.basicRes> toDtoList(List<Article> articleList) {

        return articleList.stream()
                .map(Article::toBasicRes)
                .collect(Collectors.toList());
    }

    private ArticleDto.pagingRes toPagingRes(List<ArticleDto.basicRes> articlesDto, long totalElement, int totalPage) {

        return ArticleDto.pagingRes.builder()
                .totalPage(totalPage)
                .totalElement(totalElement)
                .articles(articlesDto)
                .build();
    }

    private User findUser(String nickname) {

        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(ErrorCode.NICKNAME_NOT_FOUND));
    }
}
