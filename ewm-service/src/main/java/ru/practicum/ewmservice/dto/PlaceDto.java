package ru.practicum.ewmservice.dto;
import ru.practicum.ewmservice.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    Long id;
    String name;
    Location location;
    Float radius;
}
