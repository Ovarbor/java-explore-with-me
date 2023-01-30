package ru.practicum.statclient.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.statdto.dto.HitDto;

@Configuration
@Component
@RequiredArgsConstructor
public class WebClientConfiguration {

        private final WebClient webClient;

        public void post(String path, HitDto endpointHit) {
            webClient.post()
                    .uri(path)
                    .bodyValue(endpointHit)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        }
}
