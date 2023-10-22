package ssafy.sorhy.controller.article;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.dto.article.ArticleDto;
import ssafy.sorhy.entity.Article;
import ssafy.sorhy.service.article.ArticleService;
import ssafy.sorhy.util.Response;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public Response<ArticleDto.BasicRes> save(@RequestParam Long userId, @RequestBody ArticleDto.saveReq request) {
        ArticleDto.BasicRes response = articleService.save(userId, request);
        return new Response(201, "게시글을 정상적으로 작성했습니다.", response);
    }

    @GetMapping("/article")
    public Response<ArticleDto.DetailRes> findById(@RequestParam Long articleId) {

        ArticleDto.DetailRes response = articleService.findById(articleId);
        return new Response(200, "게시글을 조회했습니다.", response);
    }

//    @GetMapping("/articles")
//    public List<Article> findAll() {
//        return articleService
//    }


}
