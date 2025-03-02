package ssafy.sorhy.service.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserLoginRequest {

    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
}
