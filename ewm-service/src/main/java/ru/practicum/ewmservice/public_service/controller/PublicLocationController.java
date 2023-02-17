package ru.practicum.ewmservice.public_service.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmservice.dto.LocationDto;
import ru.practicum.ewmservice.public_service.service.PublicLocationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PublicLocationController {

    private final PublicLocationService publicLocationService;

    @GetMapping
    ResponseEntity<List<LocationDto>> getLocations(
                                        @RequestParam(value = "from", defaultValue = "0")
                                        @PositiveOrZero Integer from,
                                        @RequestParam(value = "size", defaultValue = "10")
                                        @Positive Integer size) {
        log.info("GET /locations?from=" + from + "&size=" + size);
        return ResponseEntity.ok().body(publicLocationService.findAllLocations(from, size));
    }

    @GetMapping("/{locId}")
    ResponseEntity<LocationDto> getLocation(@PathVariable("locId") Long locId) {
        log.info("GET /locations/{}", locId);
        return ResponseEntity.ok().body(publicLocationService.findLocation(locId));
    }
}
