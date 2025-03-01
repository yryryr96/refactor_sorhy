package ssafy.sorhy.controller.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.dto.article.ArticleDto;
import ssafy.sorhy.service.article.ArticleService;
import ssafy.sorhy.service.article.request.CreateArticleRequest;
import ssafy.sorhy.service.article.response.ArticleListResponse;
import ssafy.sorhy.service.article.response.CreateArticleResponse;
import ssafy.sorhy.util.response.ApiResponse;
import ssafy.sorhy.util.response.Response;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public ApiResponse<CreateArticleResponse> save(
            @RequestPart @Valid CreateArticleRequest request,
            @RequestPart @Nullable MultipartFile file,
            Authentication authentication) throws IOException {

        String nickname = authentication.getName();
        CreateArticleResponse response = articleService.create(nickname, file, request);
        return ApiResponse.of(HttpStatus.CREATED, "게시글을 정상적으로 작성했습니다.", response);
    }

    @GetMapping("/articles")
    public ApiResponse<ArticleListResponse> getAllArticlesByCategory(@RequestParam Category category,
                                                                     @PageableDefault(size = 4) Pageable pageable,
                                                                     Authentication authentication) {
        ArticleListResponse response;
        if (Category.isCompany(category)) {
            String nickname = authentication.getName();
            response = articleService.getAllCompanyArticles(nickname, pageable);
        } else {
            response = articleService.getAllArticlesByCategory(category, pageable);
        }

        return ApiResponse.ok("게시글 전체 조회 성공", response);
    }

    @GetMapping("/articles/hot")
    public Response<ArticleDto.pagingRes> findHotArticles(@RequestParam String category,
                                                          @PageableDefault(size = 4) Pageable pageable,
                                                          Authentication authentication) {
        String nickname = null;
        if (category.equals("COMPANY")) {
            nickname = authentication.getName();
        }
        ArticleDto.pagingRes response = articleService.findHotArticles(nickname, category, pageable);
        return new Response(200, "게시글 전체 조회 성공", response);
    }

    @GetMapping("/articles/issue")
    public Response<List<ArticleDto.issueRes>> findCurrentIssue(@PageableDefault(size = 6) Pageable pageable) {

        List<ArticleDto.issueRes> response = articleService.findCurrentIssue(pageable);
        return new Response(200, "실시간 핫이슈 조회 성공", response);
    }

    @GetMapping("/article/{articleId}")
    public Response<ArticleDto.detailRes> findById(@PathVariable Long articleId,
                                                   @PageableDefault(size = 4) Pageable pageable) {

        ArticleDto.detailRes response = articleService.findById(articleId, pageable);
        return new Response(200, "게시글을 조회했습니다.", response);
    }

    @PutMapping("/article/{articleId}")
    public Response<String> update(@PathVariable Long articleId,
                                   @RequestBody ArticleDto.saveReq request,
                                   Authentication authentication) {

        String response = articleService.update(articleId, authentication.getName(), request);
        return new Response(200, "게시글 수정 성공", response);
    }

    @DeleteMapping("/article/{articleId}")
    public Response<String> delete(@PathVariable Long articleId,
                                   Authentication authentication) {

        String nickname = authentication.getName();
        String response = articleService.delete(articleId, nickname);
        return new Response<>(204, "삭제 안료", response);
    }

    @PostMapping("/articles/search")
    public Response<ArticleDto.pagingRes> searchArticle(@RequestBody @Valid ArticleDto.searchReq request,
                                                        @PageableDefault(size = 6) Pageable pageable,
                                                        Authentication authentication) {

        ArticleDto.pagingRes response;
        String category = request.getCategory();
        if (category.equals("COMPANY")) {

            String nickname = authentication.getName();
            response = articleService.searchCompanyArticle(nickname, request, pageable);
        } else {

            response = articleService.searchArticle(request, category, pageable);
        }
        return new Response(200, "검색 성공", response);
    }
}
