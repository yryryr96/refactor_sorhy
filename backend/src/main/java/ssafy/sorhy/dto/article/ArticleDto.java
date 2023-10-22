package ssafy.sorhy.dto.article;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import ssafy.sorhy.entity.Article;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.repository.article.ArticleRepository;
import ssafy.sorhy.repository.user.UserRepository;

@Data
public class ArticleDto {

    @Data
    public static class saveReq {

        private String title;
        private String content;

        public Article toEntity(User user) {

            return Article.builder()
                    .user(user)
                    .title(this.title)
                    .content(this.content)
                    .build();
        }
    }

    @Data
    @Builder
    public static class BasicRes {

        private Long articleId;
        private Long userId;
        private String title;
    }

    @Data
    @Builder
    public static class DetailRes {

        private Long articleId;
        private String nickname;
        private String title;
        private String content;
        private String createdAt;
    }
}
