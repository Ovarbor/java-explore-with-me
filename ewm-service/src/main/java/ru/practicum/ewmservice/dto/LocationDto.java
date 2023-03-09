package ru.practicum.ewmservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

   private Long id;
   private String name;
   private Float lat;
   private Float lon;
   private Float radius;
}
