package ru.practicum.ewmservice.public_service.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.LocationDto;

import java.util.List;

@Service
public interface PublicLocationService {

    List<LocationDto> findAllLocations(int from, int size);

    LocationDto findLocation(Long placeId);
}
