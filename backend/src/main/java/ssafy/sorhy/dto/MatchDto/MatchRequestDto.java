package ssafy.sorhy.dto.MatchDto;

import lombok.Data;

@Data
public class MatchRequestDto {

    private Long gameId;
    private Long userId;
    private int score;
    private String team;
    private Long characterId;

}
