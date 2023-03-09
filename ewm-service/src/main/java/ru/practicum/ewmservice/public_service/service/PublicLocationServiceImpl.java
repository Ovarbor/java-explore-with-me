package ru.practicum.ewmservice.public_service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.LocationDto;
import ru.practicum.ewmservice.exceptions.NotFoundValidationException;
import ru.practicum.ewmservice.mapper.LocationMapper;
import ru.practicum.ewmservice.model.Location;
import ru.practicum.ewmservice.repository.LocationRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicLocationServiceImpl implements PublicLocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public List<LocationDto> findAllLocations(int from, int size) {
        List<Location> locations = locationRepository.findAll(PageRequest.of(from, size)).toList();
        return locationMapper.toLocationDtoList(locations);
    }

    @Override
    public LocationDto findLocation(Long locId) {
        Location place = locationRepository.findById(locId).orElseThrow(() ->
                new NotFoundValidationException("Place with id: " + locId + " not found"));
        return locationMapper.toLocationDto(place);
    }
}
