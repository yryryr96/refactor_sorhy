package ssafy.sorhy.dto.UserDto;

import lombok.Builder;
import lombok.Data;
import ssafy.sorhy.entity.User;

@Data
public class UserDto {

    public static class Join {

        private String email;
        private String password;
        private String nickname;

        public User toEntity() {

            return User.builder()
                    .email(this.email)
                    .password(this.password)
                    .nickname(this.nickname)
                    .build();
        }
    }

    @Builder
    public static class Response {

        private String nickname;
        private int totalScore;

    }

}
