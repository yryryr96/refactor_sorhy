package ssafy.sorhy.controller.article;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.dto.article.ArticleDto;
import ssafy.sorhy.service.article.ArticleService;
import ssafy.sorhy.util.Response;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public Response<ArticleDto.basicRes> save(@RequestParam String nickname, @RequestBody ArticleDto.saveReq request) {
        ArticleDto.basicRes response = articleService.save(nickname, request);
        return new Response(201, "게시글을 정상적으로 작성했습니다.", response);
    }

    @GetMapping("/article")
    public Response<ArticleDto.detailRes> findById(@RequestParam Long articleId) {

        ArticleDto.detailRes response = articleService.findById(articleId);
        return new Response(200, "게시글을 조회했습니다.", response);
    }

}
