package ru.practicum.statservice.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statdto.dto.StatDto;
import ru.practicum.statdto.dto.ViewStatDto;
import ru.practicum.statservice.mapper.StatMapper;
import ru.practicum.statservice.model.StatGet;
import ru.practicum.statservice.repository.HitRepository;
import ru.practicum.statservice.repository.StatRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatServiceImpl implements StatService {

    private final StatRepository repository;
    private final HitRepository appRepository;
    private final StatMapper statMapper;

    @Transactional
    public void addHit(HitDto hitDto) {
        appRepository.save(statMapper.toHit(hitDto));
        log.debug("Added hit: {}", hitDto);
    }

    @Transactional(readOnly = true)
    public List<StatDto> getStat(ViewStatDto statsParam) {
        log.debug("Get stat: {}", statsParam);
        List<StatDto> result;
        if (statsParam.getUnique() != null) {
            List<StatGet> allUnique = repository.countAllUniqueIp(statsParam.getStart(),
                    statsParam.getEnd());
            result = getUri(allUnique, statsParam.getUris());
        } else {
            List<StatGet> all = repository.countAll(statsParam.getStart(),
                    statsParam.getEnd());
            result = getUri(all, statsParam.getUris());
        }
        log.debug("Return stat: {}", result);
        return result;
    }

    private List<StatDto> getUri(List<StatGet> all, List<String> uris) {
        if (uris == null) {
            return statMapper.toStatsDtoResp(all);
        }
        return statMapper.toStatsDtoResp(all.stream()
                .filter(stat -> uris.contains(stat.getUri()))
                .collect(Collectors.toList()));
    }
}

