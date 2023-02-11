package ru.practicum.statservice.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statservice.mapper.StatMapper;
import ru.practicum.statservice.model.Hit;
import ru.practicum.statservice.model.ViewStats;
import ru.practicum.statservice.repository.HitRepository;
import ru.practicum.statservice.repository.StatRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatServiceImpl implements StatService {

    private final StatRepository repository;
    private final HitRepository appRepository;
    private final StatMapper statMapper;

    @Override
    public HitDto addHits(Hit hit) {
        return statMapper.toHitDto(appRepository.save(hit));
    }

    @Override
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (unique) {
            return repository.getStatsByCriteriaUnique(start, end, uris)
                    .stream().sorted(Comparator.comparing(ViewStats::getHits).reversed()).collect(Collectors.toList());
        } else {
            return repository.getStatsByCriteria(start, end, uris)
                    .stream().sorted(Comparator.comparing(ViewStats::getHits).reversed()).collect(Collectors.toList());
        }
    }
}

