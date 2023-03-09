package ru.practicum.ewmservice.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewmservice.model.Location;

import java.util.List;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

    @Query(value = "SELECT l.name FROM Location l")
    List<String> findLocationByName();
}
