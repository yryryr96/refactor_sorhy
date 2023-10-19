package ssafy.sorhy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ssafy.sorhy.domain.*;
import ssafy.sorhy.dto.MatchDto.MatchRequestDto;
import ssafy.sorhy.repository.GameRepository;
import ssafy.sorhy.repository.UserRepository;


@SpringBootTest
@Rollback(false)
class GameResultServiceTest {

    @Autowired
    GameResultService gameResultService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Test
    void matchingTest() {
        Game game = Game.builder()
                .gameTitle(GameTitle.BANG)
                .gameType(GameType.MULTI)
                .build();

        User user1 = User.builder()
                .email("ssafy@ssafy.com")
                .nickname("ssff")
                .totalScore(10)
                .build();

        User user2 = User.builder()
                .email("ssafy@ssafy111.com")
                .nickname("ssff11111")
                .totalScore(20)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        gameRepository.save(game);

        MatchRequestDto dto1 = MatchRequestDto.builder()
                        .gameId(game.getId())
                        .userId(user1.getId())
                        .score(10)
                        .team(UserTeam.RED)
                        .build();

        MatchRequestDto dto2 = MatchRequestDto.builder()
                .gameId(game.getId())
                .userId(user2.getId())
                .score(20)
                .team(UserTeam.BLUE)
                .build();

        gameResultService.save(dto1);
        gameResultService.save(dto2);



    }


}