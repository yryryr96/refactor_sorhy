package ssafy.sorhy.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.service.user.request.UserCreateRequest;
import ssafy.sorhy.dto.user.UserDto;
import ssafy.sorhy.dto.user.UserEachGameScore;
import ssafy.sorhy.service.user.UserService;
import ssafy.sorhy.service.user.request.UserLoginRequest;
import ssafy.sorhy.service.user.response.UserCreateResponse;
import ssafy.sorhy.service.user.response.UserLoginResponse;
import ssafy.sorhy.service.user.response.UserProfileResponse;
import ssafy.sorhy.util.response.ApiResponse;
import ssafy.sorhy.util.response.Response;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse<UserCreateResponse> save(@Valid @RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);
        return ApiResponse.of(HttpStatus.CREATED, response);
    }

    @PostMapping("/login")
    public ApiResponse<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse response = userService.login(request);
        return ApiResponse.ok(response);
    }

    @GetMapping("/profile")
    public ApiResponse<UserProfileResponse> profile(Authentication authentication) {

        String nickname = authentication.getName();
        UserProfileResponse response = userService.getProfileByNickname(nickname);
        return ApiResponse.ok(response);
    }

    @GetMapping("/{nickname}")
    public Response<UserDto.recordRes> findByNickname(@PathVariable String nickname,
                                                      @PageableDefault(size = 5) Pageable pageable) {

        UserDto.recordRes response = userService.findByNickname(nickname, pageable);
        return new Response(200, "닉네임으로 유저 전적 조회 성공", response);
    }

    @GetMapping("/eachGameScore")
    public Response<List<UserEachGameScore>> findEachGameTopScore(Authentication authentication) {

        String nickname = authentication.getName();
        List<UserEachGameScore> response = userService.eachGameTopScore(nickname);
        return new Response(200, "각 게임 별 최고 점수", response);
    }
}
