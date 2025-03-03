package ssafy.sorhy.domain.usercharacter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.sorhy.domain.character.Character;
import ssafy.sorhy.domain.user.User;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_character_id")
    private Long id;

    @Builder.Default
    private Long useCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    public void addUseCount() {
        this.useCount += 1;
    }
}
