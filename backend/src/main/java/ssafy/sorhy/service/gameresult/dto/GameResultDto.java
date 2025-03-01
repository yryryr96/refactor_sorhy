package ssafy.sorhy.service.gameresult.dto;

import lombok.Builder;
import lombok.Getter;
import ssafy.sorhy.domain.game.Game;
import ssafy.sorhy.domain.game.GameTitle;
import ssafy.sorhy.domain.game.GameType;
import ssafy.sorhy.domain.gameresult.GameResult;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GameResultDto {

    private final Long gameId;
    private final GameTitle gameTitle;
    private final GameType gameType;
    private final Long characterId;
    private final boolean isWin;
    private final LocalDateTime createdAt;
    private final int score;
    private final List<OtherUserDto> teamMember;
    private final List<OtherUserDto> enemy;

    @Builder
    private GameResultDto(Game game, GameResult gameResult, List<OtherUserDto> teamMember, List<OtherUserDto> enemy) {
        this.gameId = game.getId();
        this.gameTitle = game.getGameTitle();
        this.gameType = game.getGameType();
        this.createdAt = gameResult.getCreatedAt();
        this.score = gameResult.getScore();
        this.characterId = gameResult.getCharacterId();
        this.isWin = gameResult.isWin();
        this.teamMember = teamMember;
        this.enemy = enemy;
    }

    public static GameResultDto of(Game game, GameResult gameResult, List<OtherUserDto> teamMember, List<OtherUserDto> enemy) {
        return new GameResultDto(game, gameResult, teamMember, enemy);
    }
}
