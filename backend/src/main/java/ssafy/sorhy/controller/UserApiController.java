package ssafy.sorhy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.dto.UserDto.UserDto;
import ssafy.sorhy.entity.User;
import ssafy.sorhy.dto.UserJoinRequest;
import ssafy.sorhy.service.UserService;
import ssafy.sorhy.util.Response;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserDto.Response> save(@RequestBody UserDto.Join dto) {
        UserDto.Response response = userService.save(dto);
        return new Response(201, "회원가입에 성공했습니다.", response);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{nickname}")
    public UserDto.Response findByNickname(@PathVariable String nickname) {

        return userService.findByNickname(nickname);
    }
}
