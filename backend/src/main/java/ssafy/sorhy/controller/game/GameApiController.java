package ssafy.sorhy.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.domain.game.Game;
import ssafy.sorhy.repository.game.GameRepository;
import ssafy.sorhy.service.game.GameService;
import ssafy.sorhy.service.game.request.GameCreateRequest;
import ssafy.sorhy.service.game.response.GameCreateResponse;
import ssafy.sorhy.util.response.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameApiController {

    private final GameService gameService;
    private final GameRepository gameRepository;

    @PostMapping
    public ApiResponse<GameCreateResponse> createGame(@RequestBody GameCreateRequest request) {
        GameCreateResponse response = gameService.createGame(request);
        return ApiResponse.of(HttpStatus.CREATED, response);
    }

    @GetMapping
    public List<Game> findAll() {
        return gameRepository.findAll();
    }
}
