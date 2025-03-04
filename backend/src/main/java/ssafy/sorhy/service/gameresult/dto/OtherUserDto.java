package ssafy.sorhy.service.gameresult.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OtherUserDto {

    private final String nickname;
    private final Long characterId;

    @Builder
    private OtherUserDto(String nickname, Long characterId) {
        this.nickname = nickname;
        this.characterId = characterId;
    }

    public static OtherUserDto of(String nickname, Long characterId) {
        return OtherUserDto.builder()
                .nickname(nickname)
                .characterId(characterId)
                .build();
    }
}
