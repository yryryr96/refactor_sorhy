package ssafy.sorhy.controller.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.service.article.ArticleService;
import ssafy.sorhy.service.article.request.ArticleCreateRequest;
import ssafy.sorhy.service.article.request.ArticleSearchRequest;
import ssafy.sorhy.service.article.request.ArticleUpdateRequest;
import ssafy.sorhy.service.article.response.*;
import ssafy.sorhy.util.response.ApiResponse;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public ApiResponse<ArticleCreateResponse> create(
            @RequestPart @Valid ArticleCreateRequest request,
            @RequestPart @Nullable MultipartFile file,
            Authentication authentication) throws IOException {

        String nickname = authentication.getName();
        ArticleCreateResponse response = articleService.create(nickname, file, request);
        return ApiResponse.of(HttpStatus.CREATED, "게시글을 정상적으로 작성했습니다.", response);
    }

    @PutMapping("/article/{articleId}")
    public ApiResponse<ArticleUpdateResponse> update(@PathVariable Long articleId,
                                                     @RequestBody ArticleUpdateRequest request,
                                                     Authentication authentication) {

        ArticleUpdateResponse response = articleService.update(articleId, authentication.getName(), request);
        return ApiResponse.ok("게시글 수정 성공", response);
    }

    @DeleteMapping("/article/{articleId}")
    public ApiResponse<ArticleRemoveResponse> remove(@PathVariable Long articleId,
                                                     Authentication authentication) {

        String nickname = authentication.getName();
        ArticleRemoveResponse response = articleService.remove(articleId, nickname);
        return ApiResponse.of(HttpStatus.NO_CONTENT, "삭제 완료", response);
    }

    @GetMapping("/articles")
    public ApiResponse<ArticleListResponse> getAllArticlesByCategory(@RequestParam Category category,
                                                                     @PageableDefault(size = 4) Pageable pageable,
                                                                     Authentication authentication) {
        String nickname = authentication.getName();
        ArticleListResponse response = articleService.getAllArticlesByCategory(nickname, category, pageable);
        return ApiResponse.ok("게시글 전체 조회 성공", response);
    }

    @GetMapping("/articles/hot")
    public ApiResponse<ArticleListResponse> getHotArticles(@RequestParam Category category,
                                                           @PageableDefault(size = 4) Pageable pageable,
                                                           Authentication authentication) {
        String nickname = authentication.getName();
        ArticleListResponse response = articleService.getHotArticles(nickname, category, pageable);
        return ApiResponse.ok("게시글 전체 조회 성공", response);
    }

    @GetMapping("/articles/issue")
    public ApiResponse<ArticleListResponse> getCurrentIssue(@PageableDefault(size = 6) Pageable pageable) {
        ArticleListResponse response = articleService.getCurrentIssueArticles(pageable);
        return ApiResponse.ok("실시간 핫이슈 조회 성공", response);
    }

    @GetMapping("/article/{articleId}")
    public ApiResponse<ArticleDetailResponse> getArticleDetail(@PathVariable Long articleId,
                                                               @PageableDefault(size = 4) Pageable pageable) {

        ArticleDetailResponse response = articleService.getArticleDetail(articleId, pageable);
        return ApiResponse.ok("게시글을 조회했습니다.", response);
    }

    @GetMapping("/articles/search")
    public ApiResponse<ArticleListResponse> getArticlesBySearchCondition(@RequestBody @Valid ArticleSearchRequest request,
                                                                         @PageableDefault(size = 6) Pageable pageable,
                                                                         Authentication authentication) {
        String nickname = authentication.getName();
        ArticleListResponse response = articleService.getArticlesBySearchCondition(request, nickname, pageable);
        return ApiResponse.ok("검색 성공", response);
    }
}
