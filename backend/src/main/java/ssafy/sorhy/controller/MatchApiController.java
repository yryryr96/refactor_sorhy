package ssafy.sorhy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ssafy.sorhy.dto.MatchRequestDto;
import ssafy.sorhy.repository.MatchRepository;
import ssafy.sorhy.service.MatchService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MatchApiController {

    private final MatchService matchService;

    @PostMapping("/saveMatch")
    public void matchSave(@RequestBody MatchRequestDto dto) {
        matchService.save(dto);
    }
}
