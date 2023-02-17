package ru.practicum.ewmservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewLocationDto {

    @NotBlank
    String name;
    @NotNull
    Float radius;
    @NotNull
    Float lat;
    @NotNull
    Float lon;
}
