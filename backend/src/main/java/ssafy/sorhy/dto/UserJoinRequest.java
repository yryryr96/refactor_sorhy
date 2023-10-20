package ssafy.sorhy.dto;

import lombok.Data;
import ssafy.sorhy.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserJoinRequest {

    @NotBlank(message = "이메일을 다시 확인해주세요.")
    @Email
    private String email;
    @NotBlank(message = "닉네임을 다시 확인해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 다시 확인해주세요.")
    private String password;

    public User toEntity() {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

}
