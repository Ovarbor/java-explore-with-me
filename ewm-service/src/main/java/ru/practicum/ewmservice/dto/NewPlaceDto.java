package ru.practicum.ewmservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewmservice.model.Location;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPlaceDto {

    @NotBlank
    String name;
    @NotNull
    Location location;
    @NotNull
    Float radius;
}
