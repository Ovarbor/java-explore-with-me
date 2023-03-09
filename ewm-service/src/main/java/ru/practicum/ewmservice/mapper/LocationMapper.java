package ru.practicum.ewmservice.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewmservice.dto.LocationDto;
import ru.practicum.ewmservice.dto.NewLocationDto;
import ru.practicum.ewmservice.model.Location;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location toLocation(LocationDto locationDto);

    LocationDto toLocationDto(Location location);

    LocationDto toLocationDto(NewLocationDto newLocationDto);

    List<LocationDto> toLocationDtoList(List<Location> locationList);
}
