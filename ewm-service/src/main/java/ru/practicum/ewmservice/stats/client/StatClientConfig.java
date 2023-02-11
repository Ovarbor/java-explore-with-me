package ru.practicum.ewmservice.stats.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.statclient.config.client.StatClient;

@Configuration
public class StatClientConfig {

    @Value("${stats-server.url}")
    private String serverUrl;

    @Bean
    StatClient client() {
        return new StatClient(WebClient.create(serverUrl));
    }
}
