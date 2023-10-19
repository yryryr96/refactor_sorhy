package ssafy.sorhy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ssafy.sorhy.domain.Game;
import ssafy.sorhy.domain.GameResult;
import ssafy.sorhy.domain.User;
import ssafy.sorhy.domain.UserTeam;
import ssafy.sorhy.dto.MatchDto.MatchRequestDto;
import ssafy.sorhy.repository.GameRepository;
import ssafy.sorhy.repository.GameResultRepository;
import ssafy.sorhy.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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

        gameResultRepository.save(gameResult);
    }

    public List<GameResult> findByUserId(Long userId) {
        log.info("service={}", userId);
        return gameResultRepository.findByUserId(userId);
    }
}
