package ssafy.sorhy.dto.MatchDto;

import lombok.Builder;
import lombok.Data;
import ssafy.sorhy.domain.*;

import java.time.LocalDateTime;

@Data
public class MatchRequestDto {

    private Long gameId;
    private Long userId;
    private int score;
    private String team;
    private Long characterId;

}
