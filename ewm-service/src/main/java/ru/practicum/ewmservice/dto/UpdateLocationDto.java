package ru.practicum.ewmservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationDto {

    @NotBlank
    private String name;

    private Float radius;
    private Float lat;
    private Float lon;
}
