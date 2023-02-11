package ru.practicum.statclient.config.client;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.practicum.statdto.dto.HitShortDto;
import ru.practicum.statdto.dto.HitDto;

public class StatClient {

    private final WebClient client;

    public StatClient(WebClient client) {
        this.client = client;
    }

    public Mono<HitDto> post(HitShortDto hitShortDto) {
        return client.post()
                .uri("/hit")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(hitShortDto))
                .retrieve()
                .bodyToMono(HitDto.class);
    }
}