package ssafy.sorhy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.domain.Game;
import ssafy.sorhy.repository.GameRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    public Long createGame(Game game) {

        Game saveGame = gameRepository.save(game);
        return saveGame.getId();
    }
}
