package ru.practicum.statservice.service;
import org.springframework.stereotype.Service;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statservice.model.Hit;
import ru.practicum.statservice.model.ViewStats;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface StatService {

    HitDto addHits(Hit hit);

    List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}