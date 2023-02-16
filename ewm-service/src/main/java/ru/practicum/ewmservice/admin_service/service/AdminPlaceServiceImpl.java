package ru.practicum.ewmservice.admin_service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmservice.dto.NewPlaceDto;
import ru.practicum.ewmservice.dto.PlaceDto;
import ru.practicum.ewmservice.mapper.PlaceMapper;
import ru.practicum.ewmservice.repository.PlaceRepository;

@Service
@RequiredArgsConstructor
public class AdminPlaceServiceImpl implements AdminPlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    @Override
    @Transactional
    public PlaceDto create(NewPlaceDto newPlaceDto) {
        PlaceDto placeDto = placeMapper.toPlaceDto(newPlaceDto);
        return placeMapper.toPlaceDto(placeRepository.save(placeMapper.toPlace(placeDto)));
    }
}
