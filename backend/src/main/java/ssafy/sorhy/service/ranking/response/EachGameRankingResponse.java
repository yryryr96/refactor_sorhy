package ssafy.sorhy.service.ranking.response;

import lombok.Getter;
import ssafy.sorhy.service.ranking.dto.RankingDto;


import java.util.List;

@Getter
public class EachGameRankingResponse {

    private final List<RankingDto> rankings;

    private EachGameRankingResponse(List<RankingDto> rankings) {
        this.rankings = rankings;
    }

    public static EachGameRankingResponse of(List<RankingDto> rankings) {
        return new EachGameRankingResponse(rankings);
    }
}
