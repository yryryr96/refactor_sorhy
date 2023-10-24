package ssafy.sorhy.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.dto.user.UserDto;
import ssafy.sorhy.entity.user.User;
import ssafy.sorhy.jwt.JwtToken;
import ssafy.sorhy.jwt.JwtTokenProvider;
import ssafy.sorhy.repository.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    
    // 계정 저장
    public UserDto.joinRes save(UserDto.joinReq request) {


        User saveUser = userRepository.save(request.toEntity());
        return saveUser.toJoinDto();
    }

    // 로그인
    public JwtToken login(String username, String password) {

        // Authentication 객체 생성
        log.info("===1===");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        log.info("===2==={}, {}, {}",authenticationToken, username, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        log.info("===3===");
        // 검증된 인증 정보로 JWT 토큰 생성
        JwtToken token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
    
    // 전체 유저 조회
    public List<User> findAll() {

        return userRepository.findAll();
    }
    
    // 유저 닉네임으로 유저 정보 조회
    public UserDto.findRes findByNickname(String nickname) {

        User findUser = userRepository.findByNickname(nickname);
        return findUser.toFindDto();
    }
}
