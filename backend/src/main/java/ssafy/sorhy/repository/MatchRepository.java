package ssafy.sorhy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.sorhy.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
