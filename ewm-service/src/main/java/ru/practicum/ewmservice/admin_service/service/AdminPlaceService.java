package ru.practicum.ewmservice.admin_service.service;
import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.NewPlaceDto;
import ru.practicum.ewmservice.dto.PlaceDto;

@Service
public interface AdminPlaceService {

    PlaceDto create(NewPlaceDto place);
}
