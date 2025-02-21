package ssafy.sorhy.service.user.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.article.Article;
import ssafy.sorhy.domain.comment.Comment;
import ssafy.sorhy.domain.company.Company;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.domain.usercharacter.UserCharacter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserResponse {

    private Long id;
    private String nickname;

    @Builder
    private UserResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();
    }
}
