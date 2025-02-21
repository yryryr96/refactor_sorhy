package ssafy.sorhy.dto.gameresult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtherUserDto {

    private String nickname;
    private Long characterId;
}
