package ssafy.sorhy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ssafy.sorhy.entity.Game;
import ssafy.sorhy.entity.GameResult;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.entity.UserTeam;
import ssafy.sorhy.dto.MatchDto.MatchRequestDto;
import ssafy.sorhy.repository.GameRepository;
import ssafy.sorhy.repository.GameResultRepository;
import ssafy.sorhy.repository.UserRepository;

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

    public void createMatch(@RequestBody @Valid MatchRequestDto dto) {
        User findUser = userRepository.findById(dto.getUserId()).get();
        Game findGame = gameRepository.findById(dto.getGameId()).get();

        GameResult gameResult = GameResult.builder()
                .game(findGame)
                .user(findUser)
                .score(dto.getScore())
                .characterId(dto.getCharacterId())
                .userTeam(UserTeam.valueOf(dto.getTeam()))
                .build();

        findUser.addGameResult(gameResult);
        gameResultRepository.save(gameResult);
    }

}
