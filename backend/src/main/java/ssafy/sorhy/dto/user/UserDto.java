package ssafy.sorhy.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class top3Character {

        private Long characterId;
        private String characterName;
        private Long cnt;
    }
}
