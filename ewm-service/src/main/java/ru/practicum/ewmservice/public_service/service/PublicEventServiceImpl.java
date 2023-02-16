package ru.practicum.ewmservice.public_service.service;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.EventFullDto;
import ru.practicum.ewmservice.exceptions.ForbiddenException;
import ru.practicum.ewmservice.exceptions.NotFoundValidationException;
import ru.practicum.ewmservice.mapper.EventMapper;
import ru.practicum.ewmservice.model.*;
import ru.practicum.ewmservice.repository.EventRepository;
<<<<<<< HEAD
import ru.practicum.ewmservice.repository.PlaceRepository;

=======
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class PublicEventServiceImpl implements PublicEventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
<<<<<<< HEAD
    private final PlaceRepository placeRepository;
    private static final org.springframework.data.domain.Sort SORT_BY_DATE = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, "eventDate");
    private static final org.springframework.data.domain.Sort SORT_BY_VIEWS = org.springframework.data.domain.Sort.by(Sort.Direction.ASC, "views");
    private static final org.springframework.data.domain.Sort SORT_BY_LOCATION = org.springframework.data.domain.Sort.by(Sort.Direction.ASC, "locations");

=======
    private static final org.springframework.data.domain.Sort SORT_BY_DATE = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, "eventDate");
    private static final org.springframework.data.domain.Sort SORT_BY_VIEWS = org.springframework.data.domain.Sort.by(Sort.Direction.ASC, "views");
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3


    @Override
    public EventFullDto getEventById(Long eventId, HttpServletRequest request) {
        Event event = eventRepository.findById(eventId)
<<<<<<< HEAD
                .orElseThrow(() -> new NotFoundValidationException("Event with id: " + eventId + " not found"));

        if (event.getState().equals(EventState.PENDING) || event.getState().equals(EventState.CANCELED)) {
            throw new ForbiddenException("Event with id: " + eventId + " not published");
=======
                .orElseThrow(() -> new NotFoundValidationException("event id=" + eventId + " not found"));

        if (event.getState().equals(EventState.PENDING) || event.getState().equals(EventState.CANCELED)) {
            throw new ForbiddenException("event id=" + eventId + " not published");
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
        }
        return eventMapper.toEventFullDto(eventRepository.save(event));
    }

    @Override
<<<<<<< HEAD
    public List<EventFullDto> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd, Boolean onlyAvailable, Long placeId,
                                        ru.practicum.ewmservice.model.Sort sort,
                                        Integer from, Integer size, HttpServletRequest request) {
=======
    public List<EventFullDto> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Boolean onlyAvailable, ru.practicum.ewmservice.model.Sort sort, Integer from, Integer size, HttpServletRequest request) {
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
        Pageable page = PageRequest.of(from / size, size, SORT_BY_DATE);
        BooleanExpression expression = buildExpression(text, categories, paid, rangeStart, rangeEnd);
        if (sort.equals(ru.practicum.ewmservice.model.Sort.VIEWS)) {
            page = PageRequest.of(from / size, size, SORT_BY_VIEWS);
        }
<<<<<<< HEAD
        if (sort.equals((ru.practicum.ewmservice.model.Sort.LOCATION))) {
            page = PageRequest.of(from / size, size, org.springframework.data.domain.Sort.by(Sort.Direction.ASC, "location_lat"));
        }
=======
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
        List<Event> foundEvents = eventRepository.findAll(expression, page).getContent();
        foundEvents = foundEvents
                .stream()
                .filter(event -> event.getParticipantLimit().equals(0) ||
                        (event.getParticipantLimit() - event.getViews()) > 0)
                .collect(Collectors.toList());
        return eventMapper.toEventFullDtoList(foundEvents);
    }

    private BooleanExpression buildExpression(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        QEvent qEvent = QEvent.event;
        BooleanExpression expression = qEvent.state.eq(EventState.PUBLISHED);
        if (text != null) {
            expression = expression.and(qEvent.annotation.containsIgnoreCase(text).or(qEvent.description.containsIgnoreCase(text)));
        }
        if (categories != null) {
            expression = expression.and(qEvent.category.id.in(categories));
        }
        if (paid != null) {
            expression = expression.and(qEvent.paid.eq(paid));
        }
        if (rangeStart != null) {
            expression = expression.and(qEvent.eventDate.after(rangeStart));
        }
        if (rangeEnd != null) {
            expression = expression.and(qEvent.eventDate.before(rangeEnd));
        }
        return expression;
    }
}
