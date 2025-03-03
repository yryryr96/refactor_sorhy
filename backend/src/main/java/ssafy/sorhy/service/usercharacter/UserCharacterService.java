package ssafy.sorhy.service.usercharacter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.domain.character.Character;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.domain.usercharacter.UserCharacter;
import ssafy.sorhy.exception.ResourceNotFoundException;
import ssafy.sorhy.repository.character.CharacterRepository;
import ssafy.sorhy.repository.usercharacter.UserCharacterRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserCharacterService {

    private final UserCharacterRepository userCharacterRepository;
    private final CharacterRepository characterRepository;

    public void addCharacterUseCount(User user, Long characterId) {

        Optional<UserCharacter> userCharacterInfo = userCharacterRepository.findByUserIdAndCharacterId(user.getId(), characterId);

        if (userCharacterInfo.isEmpty()) {

            Character character = characterRepository.findById(characterId).orElseThrow(() -> new ResourceNotFoundException("Character"));
            UserCharacter newInfo = UserCharacter.builder()
                    .user(user)
                    .character(character)
                    .build();

            newInfo.addUseCount();
            userCharacterRepository.save(newInfo);
            return;
        }

        userCharacterInfo.get().addUseCount();
    }
}
