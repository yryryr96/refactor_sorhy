package ssafy.sorhy.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.sorhy.service.game.GameService;
import ssafy.sorhy.service.game.request.GameCreateRequest;
import ssafy.sorhy.service.game.response.GameCreateResponse;
import ssafy.sorhy.util.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameApiController {

    private final GameService gameService;

    @PostMapping
    public ApiResponse<GameCreateResponse> createGame(@RequestBody GameCreateRequest request) {
        GameCreateResponse response = gameService.createGame(request);
        return ApiResponse.of(HttpStatus.CREATED, response);
    }
}
