package ru.practicum.ewmservice.public_service.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.EventFullDto;
import ru.practicum.ewmservice.model.Sort;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PublicEventService {

<<<<<<< HEAD
    EventFullDto getEventById(Long eventId, HttpServletRequest request);

    List<EventFullDto> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                 Boolean onlyAvailable, Long placeId, Sort sort, Integer from, Integer size, HttpServletRequest request);
=======

    EventFullDto getEventById(Long eventId, HttpServletRequest request);

    List<EventFullDto> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                 Boolean onlyAvailable, Sort sort, Integer from, Integer size, HttpServletRequest request);
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
}
