package ssafy.sorhy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.entity.GameResult;
import ssafy.sorhy.dto.MatchDto.MatchRequestDto;
import ssafy.sorhy.service.GameResultService;
import ssafy.sorhy.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/match")
public class GameResultApiController {

    private final GameResultService gameResultService;
    private final UserService userService;

    @PostMapping
    public void matchSave(@RequestBody MatchRequestDto dto) {

        gameResultService.createMatch(dto);
    }
}
