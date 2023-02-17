package ru.practicum.ewmservice.admin_service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmservice.dto.LocationDto;
import ru.practicum.ewmservice.dto.NewLocationDto;
import ru.practicum.ewmservice.dto.UpdateLocationDto;
import ru.practicum.ewmservice.exceptions.ConflictException;
import ru.practicum.ewmservice.exceptions.NotFoundValidationException;
import ru.practicum.ewmservice.mapper.LocationMapper;
import ru.practicum.ewmservice.model.Location;
import ru.practicum.ewmservice.repository.LocationRepository;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminLocationServiceImpl implements AdminLocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    @Transactional
    public LocationDto addLocation(NewLocationDto newLocationDto) {
        LocationDto locationDto = locationMapper.toLocationDto(newLocationDto);
        return locationMapper.toLocationDto(locationRepository.save(locationMapper.toLocation(locationDto)));
    }

    @Override
    public void removeLocation(Long placeId) {
        if (!locationRepository.existsById(placeId)) {
            throw new NotFoundValidationException("User with id: " + placeId + " not found");
        }
        locationRepository.deleteById(placeId);
    }

    @Override
    @Transactional
    public LocationDto updateLocation(UpdateLocationDto updateLocationDto, Long placeId) {
        Location oldLocation = locationRepository.findById(placeId).orElseThrow(()
                -> new NotFoundValidationException("Category with id: " + placeId + " not found"));
        validate(updateLocationDto);
        Location updateLocation = locationUpdate(oldLocation, updateLocationDto);
        return locationMapper.toLocationDto(updateLocation);
    }

    private void validate(UpdateLocationDto locationDto) {
        Set<String> locationsNames = new HashSet<>(locationRepository.findLocationByName());
        if (locationsNames.contains(locationDto.getName())) {
            throw new ConflictException("Category name: " + locationDto.getName() + " already used");
        }
    }

    private Location locationUpdate(Location oldLocation, UpdateLocationDto updateLocationDto) {
        if (updateLocationDto.getName() != null) {
            if (!updateLocationDto.getName().isBlank()) {
                oldLocation.setName(updateLocationDto.getName());
            }
        }
        if (updateLocationDto.getLat() != null) {
            oldLocation.setLat(updateLocationDto.getLat());
        }
        if (updateLocationDto.getLon() != null) {
            oldLocation.setLon(updateLocationDto.getLon());
        }
        if (updateLocationDto.getRadius() != null) {
            oldLocation.setRadius(updateLocationDto.getRadius());
        }
        return oldLocation;
    }
}
