package ssafy.sorhy.service.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequest {

    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
}
