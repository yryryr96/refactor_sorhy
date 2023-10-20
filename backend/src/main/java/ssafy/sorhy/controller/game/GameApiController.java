package ssafy.sorhy.controller.game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.sorhy.dto.game.SaveGameDto;
import ssafy.sorhy.service.game.GameService;
import ssafy.sorhy.util.Response;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/game")
public class GameApiController {

    private final GameService gameService;

    @PostMapping
    public Response<SaveGameDto.Response> createGame(@RequestBody SaveGameDto.Request request) {
        SaveGameDto.Response response = gameService.createGame(request);
        return new Response(201, "게임 생성 완료", response);
    }
}
