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
import ru.practicum.ewmservice.repository.CategoryRepository;
import ru.practicum.ewmservice.repository.EventRepository;
import ru.practicum.ewmservice.repository.UserRepository;

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
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private static final org.springframework.data.domain.Sort SORT_BY_DATE = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, "eventDate");
    private static final org.springframework.data.domain.Sort SORT_BY_VIEWS = org.springframework.data.domain.Sort.by(Sort.Direction.ASC, "views");


    @Override
    public EventFullDto getEventById(Long eventId, HttpServletRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundValidationException("event id=" + eventId + " not found"));

        if (event.getState().equals(EventState.PENDING) || event.getState().equals(EventState.CANCELED)) {
            throw new ForbiddenException("event id=" + eventId + " not published");
        }

        Category eventCategory = categoryRepository.findById(event.getCategory().getId())
                .orElseThrow(() -> new NotFoundValidationException("123"));

        User eventInitiator = userRepository.findById(event.getInitiator().getId())
                .orElseThrow(() -> new NotFoundValidationException("123"));

        return eventMapper.toEventFullDto(eventRepository.save(event));
    }

    @Override
    public List<EventFullDto> getEvents(String text, Long[] categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Boolean onlyAvailable, ru.practicum.ewmservice.model.Sort sort, Integer from, Integer size, HttpServletRequest request) {
        log.debug("A list of events is requested with the following pagination parameters: from={} and size={}.", from, size);

        Pageable page = PageRequest.of(from / size, size, SORT_BY_DATE);

        BooleanExpression expression = buildExpression(text, categories, paid, rangeStart, rangeEnd);

        if (sort.equals(ru.practicum.ewmservice.model.Sort.VIEWS)) {
            page = PageRequest.of(from / size, size, SORT_BY_VIEWS);
        }

        List<Event> foundEvents = eventRepository.findAll(expression, page).getContent();

        foundEvents = foundEvents
                .stream()
                .filter(event -> event.getParticipantLimit().equals(0) ||
                        (event.getParticipantLimit() - event.getViews()) > 0)
                .collect(Collectors.toList());
//        eventClient.save(request.getRequestURI(), request.getRemoteAddr());
        log.debug("A list of events is received from repository with size of {}.", foundEvents.size());
        return eventMapper.toEventFullDtoList(foundEvents);
    }

    private BooleanExpression buildExpression(String text, Long[] categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd) {

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
