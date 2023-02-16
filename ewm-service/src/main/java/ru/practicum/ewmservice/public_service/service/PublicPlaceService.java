package ru.practicum.ewmservice.public_service.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.PlaceDto;

import java.util.List;

@Service
public interface PublicPlaceService {

    List<PlaceDto> findAllPlaces(int from, int size);

    PlaceDto findPlace(Long placeId);
}
