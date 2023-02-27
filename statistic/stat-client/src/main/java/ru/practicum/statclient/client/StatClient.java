package ru.practicum.statclient.client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.statdto.dto.HitDto;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class StatClient extends BaseClient {

    private static final String API_PREFIX = "/hit";

    @Autowired
    public StatClient(String serverUrl, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public void createHit(HttpServletRequest request) {
        HitDto hitDto = new HitDto();
        hitDto.setApp("ewm-main-service");
        hitDto.setUri(request.getRequestURI());
        hitDto.setIp(request.getRequestURI());
        hitDto.setTimestamp(LocalDateTime.now());
        post("", hitDto);
    }
}
