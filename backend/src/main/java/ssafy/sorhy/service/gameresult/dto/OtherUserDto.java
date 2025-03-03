package ssafy.sorhy.service.gameresult.dto;

public class OtherUserDto {

    private final String nickname;
    private final Long characterId;

    public OtherUserDto(String nickname, Long characterId) {
        this.nickname = nickname;
        this.characterId = characterId;
    }
}
