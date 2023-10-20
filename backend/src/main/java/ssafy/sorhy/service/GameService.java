package ssafy.sorhy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.entity.Game;
import ssafy.sorhy.dto.GameDto.SaveGameDto;
import ssafy.sorhy.repository.GameRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    public SaveGameDto.Response createGame(SaveGameDto.Request request) {
        Game game = request.toGameEntity();
        Game saveGame = gameRepository.save(game);
        return saveGame.toSaveGameDto();
    }
}
