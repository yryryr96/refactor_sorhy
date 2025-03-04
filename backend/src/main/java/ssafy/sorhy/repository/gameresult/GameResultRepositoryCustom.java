package ssafy.sorhy.repository.gameresult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.sorhy.domain.gameresult.GameResult;

public interface GameResultRepositoryCustom {

    Page<GameResult> getGameRecordInfo(Long userId, Pageable pageable);
}
