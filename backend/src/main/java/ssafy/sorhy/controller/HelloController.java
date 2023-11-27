package ssafy.sorhy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/health-check")
    public String hello() {
        return "health-check 페이지 입니다.";
    }
}
