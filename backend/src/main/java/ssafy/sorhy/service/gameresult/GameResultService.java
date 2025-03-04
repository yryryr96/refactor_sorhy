package ssafy.sorhy.service.gameresult;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.domain.game.Game;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.exception.ResourceNotFoundException;
import ssafy.sorhy.repository.game.GameRepository;
import ssafy.sorhy.repository.gameresult.GameResultRepository;
import ssafy.sorhy.repository.user.UserRepository;
import ssafy.sorhy.service.gameresult.dto.GameRecordInfo;
import ssafy.sorhy.service.gameresult.dto.GameResultDto;
import ssafy.sorhy.service.gameresult.dto.OtherUserDto;
import ssafy.sorhy.service.gameresult.request.GameResultCreateRequest;
import ssafy.sorhy.service.gameresult.response.GameResultCreateResponse;
import ssafy.sorhy.service.ranking.RankingService;
import ssafy.sorhy.service.usercharacter.UserCharacterService;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameResultService {

    private final GameResultRepository gameResultRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final UserCharacterService userCharacterService;
    private final RankingService rankingService;

    @Transactional
    public GameResultCreateResponse create(GameResultCreateRequest request, String nickname) {

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

    public GameRecordInfo getGameRecordInfo(String nickname, Pageable pageable) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));
        Page<GameResult> gameResults = gameResultRepository.getGameRecordInfo(user.getId(), pageable);
        int totalPages = gameResults.getTotalPages();

        // 게임별 내 팀 정보 초기화
        Map<Game, GameResult> myGameResults = getMyGameResults(gameResults, user);

        List<GameResultDto> result = processGameResults(gameResults.getContent(), myGameResults);

        return GameRecordInfo.of(totalPages, result);
    }

    private List<GameResultDto> processGameResults(List<GameResult> gameResults, Map<Game, GameResult> myGameResults) {
        return gameResults.stream()
                .collect(groupingBy(GameResult::getGame)) // 🔹 게임별 그룹화
                .entrySet().stream()
                .map(entry -> {
                    Game game = entry.getKey();
                    List<GameResult> results = entry.getValue();

                    // 🔹 내 팀원과 적 팀원 구분 (partitioningBy 활용)
                    Map<Boolean, List<OtherUserDto>> teamPartition = results.stream()
                            .collect(partitioningBy(
                                    gameResult -> isSameTeam(game, gameResult, myGameResults),
                                    mapping(gr -> OtherUserDto.of(gr.getUser().getNickname(), gr.getCharacterId()), toList())
                            ));

                    List<OtherUserDto> teamMember = teamPartition.get(true);
                    List<OtherUserDto> enemy = teamPartition.get(false);

                    return GameResultDto.of(game, results.get(0), teamMember, enemy);
                })
                .collect(toList());
    }

    private boolean isSameTeam(Game game, GameResult gameResult, Map<Game, GameResult> myGameResults) {
        return gameResult.getTeam().equals(myGameResults.get(game).getTeam());
    }

    private Map<Game, GameResult> getMyGameResults(Page<GameResult> gameResults, User user) {
        return gameResults.getContent().stream()
                .filter(gameResult -> gameResult.getUser().equals(user))
                .collect(toMap(GameResult::getGame, gameResult -> gameResult));
    }
}
