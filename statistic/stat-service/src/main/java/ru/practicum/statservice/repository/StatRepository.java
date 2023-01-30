package ru.practicum.statservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.statservice.model.Hit;
import ru.practicum.statservice.model.StatGet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Hit, Long> {

    @Query("SELECT s.app as app, s.uri as uri, COUNT(s.id) as hits " +
            "FROM Hit s " +
            "WHERE s.timestamp between :start AND :end " +
            "GROUP BY s.app, s.uri ORDER BY s.uri DESC")
    List<StatGet> countAll(LocalDateTime start, LocalDateTime end);

    @Query("SELECT s.app as app, s.uri as uri, COUNT(DISTINCT s.ip) as hits " +
            "FROM Hit s " +
            "WHERE s.timestamp between :start AND :end " +
            "GROUP BY s.app, s.uri ORDER BY s.uri DESC")
    List<StatGet> countAllUniqueIp(LocalDateTime start, LocalDateTime end);
}