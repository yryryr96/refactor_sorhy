package ssafy.sorhy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.sorhy.domain.Game;
import ssafy.sorhy.domain.GameTitle;
import ssafy.sorhy.domain.GameType;
import ssafy.sorhy.dto.GameDto.GameRequestDto;
import ssafy.sorhy.repository.GameRepository;
import ssafy.sorhy.service.GameService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/game")
public class GameApiController {

    private final GameService gameService;

    @PostMapping
    public Long createGame(@RequestBody GameRequestDto dto) {

        Game game = Game.builder()
                .gameType(GameType.valueOf(dto.getGameType()))
                .gameTitle(GameTitle.valueOf(dto.getGameTitle()))
                .build();
        return gameService.createGame(game);
    }
}
