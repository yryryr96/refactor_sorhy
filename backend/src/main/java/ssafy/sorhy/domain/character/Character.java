package ssafy.sorhy.domain.character;

import lombok.Getter;
import ssafy.sorhy.domain.usercharacter.UserCharacter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "Characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "character")
    private List<UserCharacter> userCharacter;
}
