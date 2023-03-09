package ru.practicum.ewmservice.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventShortDto {

    private Long id;
    private String annotation;
    private CategoryDto category;
    private Integer confirmedRequests;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
}