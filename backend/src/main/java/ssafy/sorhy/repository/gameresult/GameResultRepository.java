package ssafy.sorhy.repository.gameresult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.sorhy.dto.gameresult.OtherUserDto;
import ssafy.sorhy.domain.gameresult.GameResult;
import ssafy.sorhy.domain.gameresult.Team;

import java.util.List;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {

    @Query("select g from GameResult g join g.user u where u.id = :userId order by g.id desc")
    Page<GameResult> findByUserIdOrderByDesc(Long userId, Pageable pageable);

    @Query("select new ssafy.sorhy.dto.gameresult.OtherUserDto(u.nickname, gr.characterId) " +
            "from GameResult gr join gr.user u join gr.game g where g.id = :gameId and gr.team = :team")
    List<OtherUserDto> findTeamOtherUserDtoByGameId(Long gameId, Team team);

    @Query("select new ssafy.sorhy.dto.gameresult.OtherUserDto(u.nickname, gr.characterId) " +
            "from GameResult gr join gr.user u join gr.game g where g.id = :gameId and gr.team != :team")
    List<OtherUserDto> findEnemyOtherUserDtoByGameId(Long gameId, Team team);
}
