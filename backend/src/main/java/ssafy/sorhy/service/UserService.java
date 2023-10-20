package ssafy.sorhy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.dto.UserDto.UserDto;
import ssafy.sorhy.entity.GameResult;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.dto.UserJoinRequest;
import ssafy.sorhy.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserDto.Response save(UserDto.Join dto) {

        User saveUser = userRepository.save(dto.toEntity());
        return saveUser.toUserDto();
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public UserDto.Response findByNickname(String nickname) {

        User findUser = userRepository.findByNickname(nickname);
        return findUser.toUserDto();
    }
}
