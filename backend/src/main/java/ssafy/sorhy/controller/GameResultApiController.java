package ssafy.sorhy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ssafy.sorhy.domain.GameResult;
import ssafy.sorhy.dto.MatchDto.MatchRequestDto;
import ssafy.sorhy.service.GameResultService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/match")
public class GameResultApiController {

    private final GameResultService gameResultService;

    @PostMapping
    public void matchSave(@RequestBody MatchRequestDto dto) {

        gameResultService.createMatch(dto);
    }

    @GetMapping("/{userId}")
    public List<GameResult> findByUserId(@PathVariable Long userId) {
        log.info("{}", userId);
        List<GameResult> result = gameResultService.findByUserId(userId);
        for (GameResult gameResult : result) {
            System.out.println(gameResult.getId());
        }
        return result;
    }
}
