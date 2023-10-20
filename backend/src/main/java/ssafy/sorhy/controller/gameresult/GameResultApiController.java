package ssafy.sorhy.controller.gameresult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.service.gameresult.GameResultService;
import ssafy.sorhy.service.user.UserService;
import ssafy.sorhy.util.Response;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/gameresult")
public class GameResultApiController {

    private final GameResultService gameResultService;
    private final UserService userService;

    @PostMapping
    public Response<GameResultDto.saveRes> save(@RequestBody GameResultDto.saveReq request) {
        log.info("request={}", request);
        GameResultDto.saveRes response = gameResultService.save(request);
        return new Response(201, "게임 결과를 저장했습니다.", response);
    }
}
