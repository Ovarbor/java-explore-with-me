package ru.practicum.ewmservice.admin_service.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmservice.admin_service.service.AdminLocationService;
import ru.practicum.ewmservice.dto.LocationDto;
import ru.practicum.ewmservice.dto.NewLocationDto;
import ru.practicum.ewmservice.dto.UpdateLocationDto;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin/locations")
@RequiredArgsConstructor
@Slf4j
public class AdminLocationController {

    private final AdminLocationService adminLocationService;

    @PostMapping
    public ResponseEntity<LocationDto> postLocation(@Valid @RequestBody NewLocationDto body) {
        log.info("POST /admin/locations");
        return ResponseEntity.status(201).body(adminLocationService.addLocation(body));
    }

    @PatchMapping("/{locId}")
    public ResponseEntity<LocationDto> patchLocation(@Valid @RequestBody UpdateLocationDto updateLocationDto,
                                                     @PathVariable Long locId) {
        log.info("PATCH: /admin/locations/{}", locId);
        return ResponseEntity.ok().body(adminLocationService.updateLocation(updateLocationDto, locId));
    }

    @DeleteMapping("/{locId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long locId) {
        adminLocationService.removeLocation(locId);
        log.info("DELETE: /admin/locations/{}", locId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
