package ssafy.sorhy.dto.comment;

import lombok.Builder;
import lombok.Data;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.user.User;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CommentDto2 {

    @Data
    @Builder
    public static class basicRes {

        private Long commentId;
        private String nickname;
        private String content;
        private String createdAt;
    }


    @Data
    @Builder
    public static class pagingRes {

        private long totalElement;
        private int totalPage;
        private List<basicRes> comments;
    }
}
