package ssafy.sorhy.controller.rank;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.service.ranking.dto.CompanyRankDto;
import ssafy.sorhy.service.ranking.RankingService;
import ssafy.sorhy.service.ranking.response.EachGameRankingResponse;
import ssafy.sorhy.util.response.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankApiController {

    private final RankingService rankingService;

    @GetMapping("/{gameTitle}")
    public ApiResponse<EachGameRankingResponse> eachGameRank(@PathVariable GameTitle gameTitle,
                                                             @PageableDefault(size = 10) Pageable pageable) {

        EachGameRankingResponse response = rankingService.getEachGameRanking(gameTitle, pageable);
        return ApiResponse.ok("게임 별 랭크 조회 성공", response);
    }

    @GetMapping("/company")
    public ApiResponse<List<CompanyRankDto>> companyRank() {
        List<CompanyRankDto> response = rankingService.getCompanyRanking();
        return ApiResponse.ok("회사 별 랭크 조회 성공", response);
    }
}
