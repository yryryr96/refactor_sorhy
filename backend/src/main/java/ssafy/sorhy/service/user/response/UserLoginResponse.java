package ssafy.sorhy.service.user.response;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.company.Company;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.dto.company.CompanyInfoDto;

@Getter
@NoArgsConstructor
public class UserLoginResponse {

    @NotBlank
    private String token;

    @NotBlank
    private String nickname;

    @NotBlank
    private CompanyInfoDto company;

    @Builder
    private UserLoginResponse(String token, Company company, String nickname) {
        this.token = token;
        this.company = CompanyInfoDto.from(company);
        this.nickname = nickname;
    }

    public static UserLoginResponse of(String token, User user) {
        return new UserLoginResponse(token, user.getCompany(), user.getNickname());
    }
}
