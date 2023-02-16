package ru.practicum.ewmservice.public_service.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewmservice.dto.CompilationDto;

import java.util.List;

@Service
public interface PublicCompilationService {

    CompilationDto getCompilation(Long compId);

    List<CompilationDto> getCompilations(Boolean pinned, int from, int size);
<<<<<<< HEAD
=======

>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
}
