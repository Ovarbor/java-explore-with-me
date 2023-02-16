package ru.practicum.ewmservice.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewmservice.model.Place;

@Repository
public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {
}
