package ssafy.sorhy.service.gameresult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.dto.gameresult.OtherUserDto;
import ssafy.sorhy.domain.game.Game;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.domain.gameresult.Team;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.exception.CustomException;
import ssafy.sorhy.exception.ErrorCode;
import ssafy.sorhy.exception.ResourceNotFoundException;
import ssafy.sorhy.repository.game.GameRepository;
import ssafy.sorhy.repository.gameresult.GameResultRepository;
import ssafy.sorhy.repository.user.UserRepository;
import ssafy.sorhy.service.gameresult.request.GameResultCreateRequest;
import ssafy.sorhy.service.gameresult.response.GameResultCreateResponse;
import ssafy.sorhy.service.ranking.RankingService;
import ssafy.sorhy.service.usercharacter.UserCharacterService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GameResultService {

    private final GameResultRepository gameResultRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final UserCharacterService userCharacterService;
    private final RankingService rankingService;


    public GameResultCreateResponse save(GameResultCreateRequest request, String nickname) {

        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Game game = gameRepository.findById(request.getGameId()).orElseThrow(() -> new ResourceNotFoundException("Game"));

        Long characterId = request.getCharacterId();
        GameResult gameResult = GameResult.from(user, game, request);
        GameTitle gameTitle = game.getGameTitle();
        int score = gameResult.getScore();

        userCharacterService.addCharacterUseCount(user, characterId);
        rankingService.updateRanking(user, gameTitle, score);
        gameResultRepository.save(gameResult);
        user.updateScoreAndWinOrLose(gameResult.getScore(), gameResult.isWin());

        return GameResultCreateResponse.from(gameResult);
    }

    public GameResultDto.searchGameRecordRes getGameRecordInfo(String nickname, Pageable pageable) {

        List<GameResultDto.gameRecordInfo> result = new ArrayList<>();
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));

        Page<GameResult> gameResults = gameResultRepository.findByUserIdOrderByDesc(user.getId(), pageable);

        int totalPages = gameResults.getTotalPages();
        for (GameResult gameResult : gameResults.getContent()) {

            Game game = gameResult.getGame();
            Team team = gameResult.getTeam();
            List<OtherUserDto> teamMember = gameResultRepository.findTeamOtherUserDtoByGameId(game.getId(), team);
            List<OtherUserDto> enemy = gameResultRepository.findEnemyOtherUserDtoByGameId(game.getId(), team);

            result.add(new GameResultDto.gameRecordInfo(game, gameResult, teamMember, enemy));
        }

        return new GameResultDto.searchGameRecordRes(totalPages, result);
    }

}
