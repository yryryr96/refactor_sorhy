package ssafy.sorhy.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.dto.user.UserDto;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.repository.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserDto.joinRes save(UserDto.joinReq request) {

        User saveUser = userRepository.save(request.toEntity());
        return saveUser.toUserDto();
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

//    public UserDto.findRes findByNickname(String nickname) {
//
//        User findUser = userRepository.findByNickname(nickname);
//        findUser.get
//        return findUser.toUserDto();
//    }
}
