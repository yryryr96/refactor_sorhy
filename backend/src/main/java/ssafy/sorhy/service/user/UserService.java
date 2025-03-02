package ssafy.sorhy.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.sorhy.domain.company.Company;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.domain.usercharacter.UserCharacter;
import ssafy.sorhy.dto.character.CharacterDto;
import ssafy.sorhy.dto.user.UserEachGameScore;
import ssafy.sorhy.exception.DuplicateNicknameException;
import ssafy.sorhy.exception.ResourceNotFoundException;
import ssafy.sorhy.repository.article.ArticleRepository;
import ssafy.sorhy.repository.comment.CommentRepository;
import ssafy.sorhy.repository.company.CompanyRepository;
import ssafy.sorhy.repository.user.UserRepository;
import ssafy.sorhy.repository.usercharacter.UserCharacterRepository;
import ssafy.sorhy.service.gameresult.GameResultService;
import ssafy.sorhy.service.gameresult.dto.GameRecordInfo;
import ssafy.sorhy.service.history.HistoryService;
import ssafy.sorhy.service.user.dto.UserRankInfoDto;
import ssafy.sorhy.service.user.request.UserCreateRequest;
import ssafy.sorhy.service.user.response.UserCreateResponse;
import ssafy.sorhy.service.user.response.UserProfileResponse;
import ssafy.sorhy.service.user.response.UserRecordResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CompanyRepository companyRepository;
    private final UserCharacterRepository  userCharacterRepository;
    private final GameResultService gameResultService;
    private final HistoryService historyService;
    private final BCryptPasswordEncoder encoder;

    // 계정 저장
    @Transactional
    public UserCreateResponse createUser(UserCreateRequest request) {

        if (existedNickname(request)) {
            throw new DuplicateNicknameException();
        }

        if (doesNotMatchPasswordAndConfirmPassword(request)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->  new ResourceNotFoundException("Company"));

        User user = User.create(request, company);
        String encodedPassword = encoder.encode(user.getPassword());
        user.changeToEncodedPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        return UserCreateResponse.of(savedUser);
    }

    private boolean doesNotMatchPasswordAndConfirmPassword(UserCreateRequest request) {
        return !request.getPassword().equals(request.getConfirmPassword());
    }

    private boolean existedNickname(UserCreateRequest request) {
        return userRepository.existsByNickname(request.getNickname());
    }

    public UserProfileResponse getProfileBy(String nickname) {

        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));

        Long articleCount = articleRepository.countArticleByNickname(nickname);
        Long commentCount = commentRepository.countCommentByNickname(nickname);
        List<CharacterDto> top3Characters = getMostUse3Characters(user);

        return UserProfileResponse.of(user, articleCount, commentCount, top3Characters);
    }

    private List<CharacterDto> getMostUse3Characters(User user) {
        List<UserCharacter> mostUse3Characters =
                userCharacterRepository.findMostUse3CharactersByUserId(user.getId(), PageRequest.of(0, 3));
        return mostUse3Characters.stream()
                .map(uc -> CharacterDto.from(uc.getUseCount(), uc.getCharacter()))
                .collect(Collectors.toList());
    }

    public UserRecordResponse getUserRecordBy(String nickname, Pageable pageable) {

        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new ResourceNotFoundException("User"));

        UserRankInfoDto userRankInfo = userRepository.findUserRankInfo(nickname);

        List<CharacterDto> mostUse3Characters = getMostUse3Characters(user);
        GameRecordInfo gameRecordInfo = gameResultService.getGameRecordInfo(nickname, pageable);
        return UserRecordResponse.of(user, userRankInfo, mostUse3Characters, gameRecordInfo);
    }

    // 차후에 책임 이동 -> Game
    public List<UserEachGameScore> eachGameTopScore(String nickname) {

        return userRepository.findEachGameTopScore(nickname);
    }

    // 전체 유저 조회
    public List<User> findAll() {

        return userRepository.findAll();
    }
}
