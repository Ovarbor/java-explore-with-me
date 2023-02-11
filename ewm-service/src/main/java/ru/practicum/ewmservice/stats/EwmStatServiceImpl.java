package ru.practicum.ewmservice.stats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.practicum.statclient.config.client.StatClient;
import ru.practicum.statdto.dto.HitShortDto;
import ru.practicum.statdto.dto.HitDto;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class EwmStatServiceImpl implements EwmStatService {

    private final StatClient client;

    private static final String APP = "ewm-service";

    @Override
    public void saveHit(HttpServletRequest request) {
        getUriHit(request.getRequestURI(), request.getRemoteAddr());
    }

    private Mono<HitDto> getUriHit(String uri, String ip) {
        return client.post(HitShortDto.builder()
                .app(APP)
                .uri(uri)
                .ip(ip)
                .build());
    }
}
