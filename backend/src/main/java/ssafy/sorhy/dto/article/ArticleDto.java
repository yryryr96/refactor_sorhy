package ssafy.sorhy.dto.article;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ssafy.sorhy.dto.comment.CommentDto;
import ssafy.sorhy.entity.article.Article;
import ssafy.sorhy.entity.article.SearchCond;
import ssafy.sorhy.entity.user.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ArticleDto {

    @Data
    @Builder
    public static class basicRes {

        private Long articleId;
        private String nickname;
        private String title;
        private String createdAt;
    }

    @Data
    public static class saveReq {

        @NotBlank(message = "제목을 입력하세요.")
        private String title;

        @NotBlank(message = "내용을 입력하세요.")
        private String content;

        @NotNull
        private int companyArticle;

        public Article toEntity(User user, String imgUrl) {

            return Article.builder()
                    .user(user)
                    .title(this.title)
                    .content(this.content)
                    .companyArticle(this.companyArticle)
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
        private String createdAt;
        private String imgUrl;
    }

    @Data
    public static class searchReq {

        private String searchCond;

        @Length(min = 2)
        private String word;
    }

    @Data
    @Builder
    public static class pagingRes {

        private int totalPage;
        private long totalElement;
        private List<basicRes> articles;
    }
}
