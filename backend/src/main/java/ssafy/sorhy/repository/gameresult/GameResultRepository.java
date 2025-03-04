package ssafy.sorhy.repository.gameresult;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.sorhy.domain.gameresult.GameResult;

public interface GameResultRepository extends JpaRepository<GameResult, Long>, GameResultRepositoryCustom {
}
