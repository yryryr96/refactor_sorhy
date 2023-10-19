package ssafy.sorhy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ssafy.sorhy.domain.Game;
import ssafy.sorhy.domain.Match;
import ssafy.sorhy.domain.User;
import ssafy.sorhy.dto.MatchRequestDto;
import ssafy.sorhy.repository.GameRepository;
import ssafy.sorhy.repository.MatchRepository;
import ssafy.sorhy.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MatchService {

    private final MatchRepository matchRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public void save(@RequestBody @Valid MatchRequestDto dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Game game = gameRepository.findById(dto.getGameId()).orElseThrow();
        log.info("user={}", user);
        log.info("user={}", game);
        Match match = Match.builder()
                .user(user)
                .game(game)
                .build();

        matchRepository.save(match);
    }
}
