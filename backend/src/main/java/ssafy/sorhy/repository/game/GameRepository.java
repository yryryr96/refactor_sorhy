package ssafy.sorhy.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.sorhy.domain.game.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
