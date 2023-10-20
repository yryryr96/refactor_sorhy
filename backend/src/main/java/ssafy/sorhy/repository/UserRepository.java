package ssafy.sorhy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssafy.sorhy.entity.GameResult;
import ssafy.sorhy.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.nickname = :nickname")
    User findByNickname(@Param("nickname") String nickname);
}
