package ru.practicum.ewmservice.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewmservice.dto.NewPlaceDto;
import ru.practicum.ewmservice.dto.PlaceDto;
import ru.practicum.ewmservice.model.Place;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    Place toPlace(PlaceDto placeDto);

    PlaceDto toPlaceDto(Place place);

    PlaceDto toPlaceDto(NewPlaceDto newPlaceDto);

    List<PlaceDto> toPlaceDtoList(List<Place> placeList);
}
