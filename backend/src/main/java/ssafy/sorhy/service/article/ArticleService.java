package ssafy.sorhy.service.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ssafy.sorhy.dto.article.ArticleDto;
import ssafy.sorhy.entity.Article;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.repository.article.ArticleRepository;
import ssafy.sorhy.repository.user.UserRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleDto.BasicRes save(Long userId, ArticleDto.saveReq request) {
        User user = userRepository.findById(userId).get();
        Article article = request.toEntity(user);
        articleRepository.save(article);

        ArticleDto.BasicRes response = article.toBasicRes();
        return response;
    }

    public ArticleDto.DetailRes findById(Long articleId) {

        Article article = articleRepository.findById(articleId).get();
        return article.toDetailRes();
    }
}
