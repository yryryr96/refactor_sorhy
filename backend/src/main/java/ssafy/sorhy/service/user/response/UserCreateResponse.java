package ssafy.sorhy.service.user.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.user.User;

@Getter
public class UserCreateResponse {

    private Long id;
    private String nickname;

    @Builder
    private UserCreateResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public static UserCreateResponse of(User user) {
        return UserCreateResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();
    }
}
