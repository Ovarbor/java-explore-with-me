package ru.practicum.statservice.mapper;
import org.mapstruct.Mapper;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statdto.dto.StatDto;
import ru.practicum.statservice.model.Hit;
import ru.practicum.statservice.model.StatGet;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatMapper {

    List<StatDto> toStatsDtoResp(List<StatGet> stat);

    Hit toHit(HitDto hitDto);
}
