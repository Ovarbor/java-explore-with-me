package ru.practicum.ewmservice.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.ewmservice.model.EventState;
import ru.practicum.ewmservice.model.Location;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventFullDto {

   private Long id;
   private String annotation;
   private CategoryDto category;
   private Long confirmedRequests;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdOn;
   private String description;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime eventDate;

   private UserShortDto initiator;
   private Location location;
   private Boolean paid;
   private Short participantLimit;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime publishedOn;

   private Boolean requestModeration;
   private EventState state;
   private String title;
   private Long views;
}
