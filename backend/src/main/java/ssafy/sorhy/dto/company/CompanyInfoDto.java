package ssafy.sorhy.dto.company;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.company.Company;

@Getter
public class CompanyInfoDto {

    private Long id;
    private String companyName;

    @Builder
    private CompanyInfoDto(Long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public static CompanyInfoDto from(Company company) {
        return CompanyInfoDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .build();
    }
}
