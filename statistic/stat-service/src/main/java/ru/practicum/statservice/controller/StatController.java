package ru.practicum.statservice.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statservice.model.Hit;
import ru.practicum.statservice.model.ViewStats;
import ru.practicum.statservice.service.StatService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class StatController {

    private final StatService service;

    @PostMapping("/hit")
    public HitDto saveStat(@RequestBody @Valid Hit hit) {
        log.info("POST: /hit");
        return service.addHits(hit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStat(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                         LocalDateTime start,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                         LocalDateTime end,
                                     @RequestParam List<String> uris,
                                     @RequestParam(defaultValue = "false") boolean unique) {
        log.info("GET: /stats");
        return service.getStats(start, end, uris, unique);
    }
}
