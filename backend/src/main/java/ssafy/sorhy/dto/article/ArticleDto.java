package ssafy.sorhy.dto.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.article.Category;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.dto.comment.CommentDto2;

import java.util.List;

@Data
public class ArticleDto {

    @Data
    @Builder
    public static class basicRes {

        private Long articleId;
        private String nickname;
        private String title;
        private int viewCount;
        private String imageUrl;
        private String createdAt;
    }

    @Data
    @Builder
    public static class issueRes {
        private Long articleId;
        private String title;
        private Category category;
    }

    @Data
    public static class saveReq {

        @NotBlank(message = "제목을 입력하세요.")
        private String title;

        @NotBlank(message = "내용을 입력하세요.")
        private String content;

        @NotNull
        private String category;

        public Article toEntity(User user, String imgUrl) {

            return Article.builder()
                    .user(user)
                    .title(this.title)
                    .content(this.content)
                    .category(Category.valueOf(this.category))
                    .imgUrl(imgUrl)
                    .build();
        }
    }

    @Data
    @Builder
    public static class detailRes {

        private Long articleId;
        private String nickname;
        private String title;
        private String content;
        private Category category;
        private int viewCount;
        private String createdAt;
        private String imgUrl;
        private CommentDto2.pagingRes comments;
    }

    @Data
    public static class searchReq {

        private String searchCond;

        @Length(min = 2)
        private String word;
        private String category;
    }

    @Data
    @Builder
    public static class pagingRes {

        private int totalPage;
        private long totalElement;
        private List<basicRes> articles;
    }
}
