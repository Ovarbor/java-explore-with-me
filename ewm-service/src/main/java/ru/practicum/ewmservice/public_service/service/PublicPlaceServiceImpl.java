package ru.practicum.ewmservice.public_service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.PlaceDto;
import ru.practicum.ewmservice.exceptions.NotFoundValidationException;
import ru.practicum.ewmservice.mapper.PlaceMapper;
import ru.practicum.ewmservice.model.Place;
import ru.practicum.ewmservice.repository.PlaceRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicPlaceServiceImpl implements PublicPlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    @Override
    public List<PlaceDto> findAllPlaces(int from, int size) {
        List<Place> places = placeRepository.findAll(PageRequest.of(from, size)).toList();
        return placeMapper.toPlaceDtoList(places);
    }

    @Override
    public PlaceDto findPlace(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new NotFoundValidationException("Place with id: " + placeId + " not found"));
        return placeMapper.toPlaceDto(place);
    }
}
