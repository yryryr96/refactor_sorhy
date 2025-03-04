package ssafy.sorhy.service.gameresult.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GameRecordInfo {

    private final int totalPage;
    private final List<GameResultDto> gameResults;

    @Builder
    private GameRecordInfo(int totalPage, List<GameResultDto> gameResults) {
        this.totalPage = totalPage;
        this.gameResults = gameResults;
    }

    public static GameRecordInfo of(int totalPage, List<GameResultDto> gameResultDto) {
        return GameRecordInfo.builder()
                .totalPage(totalPage)
                .gameResults(gameResultDto)
                .build();
    }
}
