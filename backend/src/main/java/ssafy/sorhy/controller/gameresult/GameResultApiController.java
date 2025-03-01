package ssafy.sorhy.controller.gameresult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.service.gameresult.GameResultService;
import ssafy.sorhy.service.gameresult.request.GameResultCreateRequest;
import ssafy.sorhy.service.gameresult.response.GameResultCreateResponse;
import ssafy.sorhy.util.response.ApiResponse;
import ssafy.sorhy.util.response.Response;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/gameresult")
public class GameResultApiController {

    private final GameResultService gameResultService;

    @PostMapping
    public ApiResponse<GameResultCreateResponse> save(@RequestBody GameResultCreateRequest request,
                                                      Authentication authentication) {

        String nickname = authentication.getName();
        GameResultCreateResponse response = gameResultService.save(request, nickname);
        return ApiResponse.of(HttpStatus.CREATED, "게임 결과를 저장했습니다.", response);
    }
}
