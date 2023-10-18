package ssafy.sorhy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.domain.User;
import ssafy.sorhy.dto.UserJoinRequest;
import ssafy.sorhy.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void save(UserJoinRequest dto) {
        User user = dto.toEntity();
        userRepository.save(user);
    }
}
