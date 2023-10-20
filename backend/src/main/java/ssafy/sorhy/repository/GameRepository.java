package ssafy.sorhy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.sorhy.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
