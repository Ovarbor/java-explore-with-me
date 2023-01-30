package ru.practicum.statservice.service;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statdto.dto.StatDto;
import ru.practicum.statdto.dto.ViewStatDto;

import java.util.List;

public interface StatService {

    void addHit(HitDto hitDto);

    List<StatDto> getStat(ViewStatDto statsParam);
}