package ssafy.sorhy.dto.MatchDto;

import lombok.Data;
import ssafy.sorhy.domain.UserTeam;
@Data
public class MatchResponseDto {

    private Long gameId;
    private Long userId;
    private int score;
    private UserTeam team;
    private Long characterId;

}
