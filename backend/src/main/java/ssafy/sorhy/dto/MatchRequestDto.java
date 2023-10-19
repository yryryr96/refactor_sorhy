package ssafy.sorhy.dto;

import lombok.Builder;
import lombok.Data;
import ssafy.sorhy.domain.*;

import java.time.LocalDateTime;

@Data
@Builder
public class MatchRequestDto {

    private Long gameId;
    private Long userId;
    private int score;

    @Builder.Default
    private LocalDateTime localDateTime = LocalDateTime.now();
    private UserTeam team;

}
