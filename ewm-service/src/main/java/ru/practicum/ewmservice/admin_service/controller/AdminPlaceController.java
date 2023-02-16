package ru.practicum.ewmservice.admin_service.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewmservice.admin_service.service.AdminPlaceService;
import ru.practicum.ewmservice.dto.NewPlaceDto;
import ru.practicum.ewmservice.dto.PlaceDto;
import javax.validation.Valid;


@RestController
@RequestMapping("/admin/places")
@RequiredArgsConstructor
@Slf4j
public class AdminPlaceController {

    private final AdminPlaceService adminPlaceService;

    @PostMapping
    public ResponseEntity<PlaceDto> postPlace(@Valid @RequestBody NewPlaceDto body) {
        log.info("POST /places");
        return ResponseEntity.ok().body(adminPlaceService.create(body));
    }
}
