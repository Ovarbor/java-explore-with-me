package ru.practicum.statservice.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statdto.dto.StatDto;
import ru.practicum.statdto.dto.ViewStatDto;
import ru.practicum.statservice.service.StatService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatController {

    private final StatService service;

    @PostMapping("/hit")
    public ResponseEntity<Void> saveStat(@RequestBody HitDto endpointHit) {
        service.addHit(endpointHit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<StatDto>> getStat(@Valid ViewStatDto statsParam) {
        return ResponseEntity.ok().body(service.getStat(statsParam));
    }
}
