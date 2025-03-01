package ssafy.sorhy.service.gameresult.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GameRecordInfo {

    private final int totalPage;
    private final List<GameResultDto> gameResultDto;

    @Builder
    private GameRecordInfo(int totalPage, List<GameResultDto> gameResultDto) {
        this.totalPage = totalPage;
        this.gameResultDto = gameResultDto;
    }

    public static GameRecordInfo of(int totalPage, List<GameResultDto> gameResultDto) {
        return GameRecordInfo.builder()
                .totalPage(totalPage)
                .gameResultDto(gameResultDto)
                .build();
    }
}
