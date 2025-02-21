package ssafy.sorhy.repository.usercharacter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.sorhy.domain.usercharacter.UserCharacter;

import java.util.List;
import java.util.Optional;

public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {

    @Query("select uc from UserCharacter uc join fetch uc.user u join fetch uc.character c " +
            "where u.id = :userId and c.id = :characterId")
    Optional<UserCharacter> findByUserIdAndCharacterId(Long userId, Long characterId);

    @Query(nativeQuery = true,
            value = "select * from user_character where user_id = :userId order by use_count desc limit 3")
    List<UserCharacter> findTop3CharacterByUserId(Long userId);

    @Query("select uc from UserCharacter uc " +
            "join fetch uc.user u " +
            "where u.id = :userId " +
            "order by uc.useCount desc")
    List<UserCharacter> findMostUse3CharactersByUserId(Long userId, Pageable pageable);
}
