package ru.practicum.ewmservice.stats;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public interface EwmStatService {

    void saveHit(HttpServletRequest request);
}
