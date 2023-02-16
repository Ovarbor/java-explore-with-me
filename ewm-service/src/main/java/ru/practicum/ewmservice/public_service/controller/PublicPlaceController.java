package ru.practicum.ewmservice.public_service.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmservice.dto.PlaceDto;
import ru.practicum.ewmservice.public_service.service.PublicPlaceService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PublicPlaceController {

    private final PublicPlaceService publicPlaceService;

    @GetMapping
    ResponseEntity<List<PlaceDto>> getPlaces(
                                        @RequestParam(value = "from", defaultValue = "0")
                                        @PositiveOrZero Integer from,
                                        @RequestParam(value = "size", defaultValue = "10")
                                        @Positive Integer size) {
        log.info("GET /places?from=" + from + "&size=" + size);
        return ResponseEntity.ok().body(publicPlaceService.findAllPlaces(from, size));
    }

    @GetMapping("/{placeId}")
    ResponseEntity<PlaceDto> getPlace(@PathVariable("placeId") Long placeId) {
        log.info("GET /places/" + placeId);
        return ResponseEntity.ok().body(publicPlaceService.findPlace(placeId));
    }
}
