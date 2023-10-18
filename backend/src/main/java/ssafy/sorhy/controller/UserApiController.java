package ssafy.sorhy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ssafy.sorhy.domain.User;
import ssafy.sorhy.dto.UserJoinRequest;
import ssafy.sorhy.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@Valid @RequestBody UserJoinRequest dto) {
        userService.save(dto);
        return dto.getEmail();
    }
}
