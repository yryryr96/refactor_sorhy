package ssafy.sorhy.controller.gameresult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.service.gameresult.GameResultService;
import ssafy.sorhy.service.gameresult.dto.GameRecordInfo;
import ssafy.sorhy.service.gameresult.request.GameResultCreateRequest;
import ssafy.sorhy.service.gameresult.response.GameResultCreateResponse;
import ssafy.sorhy.util.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/gameresult")
public class GameResultApiController {

    private final GameResultService gameResultService;

    @PostMapping
    public ApiResponse<GameResultCreateResponse> createGameResult(@RequestBody @Valid GameResultCreateRequest request,
                                                                  Authentication authentication) {

        String nickname = authentication.getName();
        GameResultCreateResponse response = gameResultService.create(request, nickname);
        return ApiResponse.of(HttpStatus.CREATED, "게임 결과를 저장했습니다.", response);
    }

    @GetMapping("/{nickname}")
    public GameRecordInfo getGameResultInfo(@PathVariable String nickname, Pageable pageable) {
        return gameResultService.getGameRecordInfo(nickname, pageable);
    }
}
