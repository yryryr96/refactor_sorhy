package ssafy.sorhy.dto.character;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.character.Character;

@Getter
public class CharacterDto {
    private Long id;
    private String name;
    private Long useCount;

    @Builder
    private CharacterDto(Long id, String name, Long useCount) {
        this.id = id;
        this.name = name;
        this.useCount = useCount;
    }

    public static CharacterDto from(Long useCount, Character character) {
        return CharacterDto.builder()
                .id(character.getId())
                .name(character.getName())
                .useCount(useCount)
                .build();
    }
}
