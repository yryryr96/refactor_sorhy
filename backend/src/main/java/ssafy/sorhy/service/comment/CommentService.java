package ssafy.sorhy.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.dto.comment.CommentDto2;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.exception.CustomException;
import ssafy.sorhy.exception.ErrorCode;
import ssafy.sorhy.repository.article.ArticleRepository;
import ssafy.sorhy.repository.comment.CommentRepository;
import ssafy.sorhy.repository.user.UserRepository;
import ssafy.sorhy.service.comment.dto.CommentDto;
import ssafy.sorhy.service.comment.response.CommentsResponse;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public CommentDto2.basicRes save(Long articleId, String nickname, CommentDto2.saveReq request) {

        User user = findUser(nickname);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        Comment comment = request.toEntity(user, article);
        commentRepository.save(comment);
        return comment.toBasicRes();
    }

    public String delete(Long commentId, String nickname) {

        User user = findUser(nickname);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        if (comment.getUser().equals(user)) {

            commentRepository.delete(comment);
            return "delete success";
        }

        throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
    }

    public String update(Long commentId, String nickname, CommentDto2.saveReq request) {

        User user = findUser(nickname);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_FOUND));

        if (comment.getUser().equals(user)) {

            comment.update(request);
            return "update success!";
        }

        throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
    }

//    public CommentDto2.pagingRes getCommentsBy(Long articleId, Pageable pageable) {
//
//        Page<Comment> result = commentRepository.findByArticleIdOrderByIdDesc(articleId, pageable);
//        List<CommentDto2.basicRes> comments = result.stream()
//                .map(Comment::toBasicRes)
//                .collect(Collectors.toList());
//
//        return CommentDto2.pagingRes.builder()
//                .comments(comments)
//                .totalElement(result.getTotalElements())
//                .totalPage(result.getTotalPages())
//                .build();
//    }

    public CommentsResponse getCommentsBy(Long articleId, Pageable pageable) {

        Page<Comment> result = commentRepository.findByArticleIdOrderByIdDesc(articleId, pageable);
        return CommentsResponse.of(result);
    }

    private User findUser(String nickname) {
        return userRepository.findByNickname(nickname).orElseThrow(() -> new CustomException(ErrorCode.NICKNAME_NOT_FOUND));
    }
}
