package ssafy.sorhy.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.exception.CustomException;
import ssafy.sorhy.exception.ErrorCode;
import ssafy.sorhy.exception.ResourceNotFoundException;
import ssafy.sorhy.exception.UnAuthorizedException;
import ssafy.sorhy.repository.article.ArticleRepository;
import ssafy.sorhy.repository.comment.CommentRepository;
import ssafy.sorhy.repository.user.UserRepository;
import ssafy.sorhy.service.comment.request.CommentCreateRequest;
import ssafy.sorhy.service.comment.request.CommentUpdateRequest;
import ssafy.sorhy.service.comment.response.CommentCreateResponse;
import ssafy.sorhy.service.comment.response.CommentRemoveResponse;
import ssafy.sorhy.service.comment.response.CommentUpdateResponse;
import ssafy.sorhy.service.comment.response.CommentsResponse;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentCreateResponse create(Long articleId, String nickname, CommentCreateRequest request) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article"));

        Comment comment = Comment.from(request, user, article);
        Comment savedComment = commentRepository.save(comment);
        return CommentCreateResponse.from(savedComment, user);
    }

    @Transactional
    public CommentRemoveResponse remove(Long commentId, String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment"));

        if (comment.getUser().equals(user)) {
            commentRepository.delete(comment);
            return CommentRemoveResponse.of("댓글 삭제 완료");
        }

        throw new UnAuthorizedException();
    }

    @Transactional
    public CommentUpdateResponse update(Long commentId, String nickname, CommentUpdateRequest request) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        if (comment.getUser().equals(user)) {
            comment.update(request);
            return CommentUpdateResponse.of("댓글 수정 완료");
        }

        throw new UnAuthorizedException();
    }

    public CommentsResponse getCommentsBy(Long articleId, Pageable pageable) {
        Page<Comment> result = commentRepository.findByArticleIdOrderByIdDesc(articleId, pageable);
        return CommentsResponse.of(result);
    }
}
