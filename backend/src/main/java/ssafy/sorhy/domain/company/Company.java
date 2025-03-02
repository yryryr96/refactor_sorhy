package ssafy.sorhy.domain.company;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.user.User;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String companyName;
    private int companyScore;

    @OneToMany(mappedBy = "company")
    private List<User> users;

    public void updateCompanyScore(int userScore) {
        this.companyScore += userScore;
    }
}
