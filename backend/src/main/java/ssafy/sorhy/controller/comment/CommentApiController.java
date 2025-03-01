package ssafy.sorhy.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.dto.comment.CommentDto2;
import ssafy.sorhy.service.comment.CommentService;
import ssafy.sorhy.service.comment.request.CommentCreateRequest;
import ssafy.sorhy.service.comment.request.CommentUpdateRequest;
import ssafy.sorhy.service.comment.response.CommentCreateResponse;
import ssafy.sorhy.service.comment.response.CommentRemoveResponse;
import ssafy.sorhy.service.comment.response.CommentUpdateResponse;
import ssafy.sorhy.service.comment.response.CommentsResponse;
import ssafy.sorhy.util.response.ApiResponse;
import ssafy.sorhy.util.response.Response;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class CommentApiController {

    private final CommentService commentService;

    @GetMapping("/{articleId}/comment")
    public ApiResponse<CommentsResponse> getCommentsByArticleId(@PathVariable Long articleId,
                                                                @PageableDefault(size = 6) Pageable pageable) {

        CommentsResponse response = commentService.getCommentsBy(articleId, pageable);
        return ApiResponse.ok( "댓글 조회 성공", response);
    }

    @PostMapping("/{articleId}/comment")
    public ApiResponse<CommentCreateResponse> create(@PathVariable Long articleId,
                                        @RequestBody @Valid CommentCreateRequest request,
                                        Authentication authentication) {

        String nickname = authentication.getName();
        CommentCreateResponse response = commentService.create(articleId, nickname, request);
        return ApiResponse.of(HttpStatus.CREATED, "댓글 생성 완료", response);
    }

    @DeleteMapping("/{articleId}/comment/{commentId}")
    public ApiResponse<CommentRemoveResponse> remove(@PathVariable Long commentId,
                                                     Authentication authentication) {

        String nickname = authentication.getName();
        CommentRemoveResponse response = commentService.remove(commentId, nickname);
        return ApiResponse.of(HttpStatus.NO_CONTENT, response);
    }

    @PutMapping("/{articleId}/comment/{commentId}")
    public ApiResponse<CommentUpdateResponse> update(@PathVariable Long commentId,
                                   @RequestBody @Valid CommentUpdateRequest request,
                                   Authentication authentication) {

        String nickname = authentication.getName();
        CommentUpdateResponse response = commentService.update(commentId, nickname, request);
        return ApiResponse.ok(response);
    }
}
