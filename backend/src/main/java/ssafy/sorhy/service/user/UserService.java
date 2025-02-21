package ssafy.sorhy.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.dto.gameresult.GameResultDto;
import ssafy.sorhy.dto.user.UserCreateRequest;
import ssafy.sorhy.dto.user.UserDto;
import ssafy.sorhy.dto.user.UserEachGameScore;
import ssafy.sorhy.dto.user.UserRankInfoDto;
import ssafy.sorhy.domain.company.Company;
import ssafy.sorhy.domain.log.LoginHistory;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.exception.CustomException;
import ssafy.sorhy.exception.DuplicateNicknameException;
import ssafy.sorhy.exception.ErrorCode;
import ssafy.sorhy.jwt.JwtTokenUtil;
import ssafy.sorhy.repository.article.ArticleRepository;
import ssafy.sorhy.repository.comment.CommentRepository;
import ssafy.sorhy.repository.company.CompanyRepository;
import ssafy.sorhy.repository.user.UserRepository;
import ssafy.sorhy.service.gameresult.GameResultService;
import ssafy.sorhy.service.history.HistoryService;
import ssafy.sorhy.service.user.response.UserResponse;
import ssafy.sorhy.service.usercharacter.UserCharacterService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CompanyRepository companyRepository;
    private final GameResultService gameResultService;
    private final UserCharacterService userCharacterService;
    private final HistoryService historyService;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String secretKey;

    // 계정 저장
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        if (existedNickname(request)) {
            throw new DuplicateNicknameException("이미 존재하는 닉네임입니다.");
        }

        if (doesNotMatchPasswordAndConfirmPassword(request)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->  new IllegalArgumentException("해당 회사 데이터를 찾을 수 없습니다."));

        User user = User.create(request, company);
        String encodedPassword = encoder.encode(user.getPassword());
        user.changeToEncodedPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        return UserResponse.of(savedUser);
    }

    private boolean doesNotMatchPasswordAndConfirmPassword(UserCreateRequest request) {
        return !request.getPassword().equals(request.getConfirmPassword());
    }

    private boolean existedNickname(UserCreateRequest request) {
        return userRepository.existsByNickname(request.getNickname());
    }

    // 로그인
    @Transactional
    public UserDto.loginRes login(UserDto.loginReq request) {

        String nickname = request.getNickname();

        if (!userRepository.existsByNickname(nickname)) {

            throw new CustomException(ErrorCode.DATA_NOT_FOUND);
        }

        User user = findUser(nickname);
        if (encoder.matches(request.getPassword(), user.getPassword())) {

            String token = JwtTokenUtil.createToken(user.getNickname(), secretKey, 60 * 1000 * 60 * 24);// 만료시간 하루
            historyService.save(new LoginHistory(nickname));
            return UserDto.loginRes.builder()
                    .token(token)
                    .companyId(user.getCompany().getId())
                    .nickname(nickname)
                    .build();
        } else {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
    }

    private User findUser(String nickname) {
        return userRepository.findByNickname(nickname).orElseThrow(() -> new CustomException(ErrorCode.NICKNAME_NOT_FOUND));
    }

    public UserDto.profileRes findProfileByNickname(String nickname) {

        User user = findUser(nickname);
        Long articleCount = articleRepository.countArticleByNickname(nickname);
        Long commentCount = commentRepository.countCommentByNickname(nickname);
        List<UserDto.top3Character> top3Characters = userCharacterService.findTop3Character(user.getId());

        return user.toProfileDto(articleCount, commentCount, top3Characters);
    }

    // 전체 유저 조회
    public List<User> findAll() {

        return userRepository.findAll();
    }

    // 유저 닉네임으로 유저 정보 조회
    public UserDto.recordRes findByNickname(String nickname, Pageable pageable) {

        User user = findUser(nickname);

        UserRankInfoDto userRankInfo = userRepository.findUserRankInfo(nickname);
        Long personalRanking = userRankInfo.getPersonalRank();
        double rankPercent = userRankInfo.getRankPercent();

        List<UserDto.top3Character> top3Characters = userCharacterService.findTop3Character(user.getId());
        GameResultDto.searchGameRecordRes gameResults = gameResultService.getGameRecordInfo(nickname, pageable);
        return user.toRecordRes(top3Characters, gameResults, personalRanking, rankPercent);
    }

    public List<UserEachGameScore> eachGameTopScore(String nickname) {

        return userRepository.findEachGameTopScore(nickname);
    }
}
