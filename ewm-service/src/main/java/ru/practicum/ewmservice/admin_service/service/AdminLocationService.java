package ru.practicum.ewmservice.admin_service.service;
import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.LocationDto;
import ru.practicum.ewmservice.dto.NewLocationDto;
import ru.practicum.ewmservice.dto.UpdateLocationDto;

@Service
public interface AdminLocationService {

    LocationDto addLocation(NewLocationDto newLocationDto);

    void removeLocation(Long locId);

    LocationDto updateLocation(UpdateLocationDto updateLocationDto, Long locId);
}
