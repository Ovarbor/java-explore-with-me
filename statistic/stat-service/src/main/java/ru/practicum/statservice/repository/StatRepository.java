package ru.practicum.statservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.statservice.model.Hit;
import ru.practicum.statservice.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Hit, Long> {

    @Query(" SELECT e.app AS app, e.uri AS uri, COUNT(e.id) AS hits " +
            "FROM Hit e " +
            " WHERE e.uri IN :uris " +
            " AND e.timestamp BETWEEN :start AND :end " +
            " GROUP BY e.app, e.uri, e.ip ")
    List<ViewStats> getStatsByCriteriaUnique(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(" SELECT e.app AS app, e.uri AS uri, COUNT(e.id) AS hits " +
            "FROM Hit e " +
            " WHERE e.uri IN :uris " +
            " AND e.timestamp BETWEEN :start AND :end " +
            " GROUP BY e.app, e.uri ")
    List<ViewStats> getStatsByCriteria(LocalDateTime start, LocalDateTime end, List<String> uris);
}