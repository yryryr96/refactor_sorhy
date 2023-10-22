package ssafy.sorhy.service.gameresult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.entity.game.Game;
import ssafy.sorhy.entity.gameresult.GameResult;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.entity.UserTeam;
import ssafy.sorhy.repository.game.GameRepository;
import ssafy.sorhy.repository.gameresult.GameResultRepository;
import ssafy.sorhy.repository.user.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GameResultService {

    private final GameResultRepository gameResultRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameResultDto.saveRes save(@RequestBody @Valid GameResultDto.saveReq request) {
        User findUser = userRepository.findById(request.getUserId()).get();
        Game findGame = gameRepository.findById(request.getGameId()).get();

        GameResult gameResult = GameResult.builder()
                .game(findGame)
                .user(findUser)
                .score(request.getScore())
                .characterId(request.getCharacterId())
                .userTeam(UserTeam.valueOf(request.getTeam()))
                .build();

        findUser.addGameResult(gameResult);

        gameResultRepository.save(gameResult);
        return gameResult.toSaveResDto(gameResult);
    }
}
