package ssafy.sorhy.dto.GameDto;

import lombok.Data;
import ssafy.sorhy.domain.Game;
import ssafy.sorhy.domain.GameTitle;
import ssafy.sorhy.domain.GameType;

@Data
public class GameRequestDto {

    private String gameType;
    private String gameTitle;
}
