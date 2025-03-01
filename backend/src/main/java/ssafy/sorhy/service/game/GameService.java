package ssafy.sorhy.service.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.domain.game.Game;
import ssafy.sorhy.repository.game.GameRepository;
import ssafy.sorhy.service.game.request.GameCreateRequest;
import ssafy.sorhy.service.game.response.GameCreateResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;

    @Transactional
    public GameCreateResponse createGame(GameCreateRequest request) {
        Game game = Game.from(request);
        Game savedGame = gameRepository.save(game);
        return GameCreateResponse.from(savedGame);
    }
}
