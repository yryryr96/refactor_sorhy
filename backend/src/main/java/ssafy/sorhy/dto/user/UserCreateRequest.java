package ssafy.sorhy.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.company.Company;
import ssafy.sorhy.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{5,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 5~20자리여야 합니다.")
    private String nickname;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotNull
    private Long companyId;

    public User toEntity(Company company) {

        return User.builder()
                .password(this.password)
                .nickname(this.nickname)
                .company(company)
                .build();
    }
}
