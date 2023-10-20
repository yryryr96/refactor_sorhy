package ssafy.sorhy.repository.gameresult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssafy.sorhy.entity.gameresult.GameResult;

import java.util.List;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {

    @Query("select g from GameResult g join fetch User u where g.user.id = :userId")
    List<GameResult> findByUserId(@Param("userId") Long userId);
}
