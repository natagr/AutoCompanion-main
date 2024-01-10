package com.auto.companion.domain.repository;

import com.auto.companion.domain.model.Event;
import com.auto.companion.event.model.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDateBetween(LocalDate start, LocalDate end);

    Optional<Event> findByIdAndUserId(Long eventId, Long userId);

//    Page<Event> findAllByIdAndUserId(Long userId, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.user.id =:userId AND e.date >= :start AND e.date <= :end")
    List<Event> findByMonthAndYear(@Param("start") LocalDate start,
                                   @Param("end") LocalDate end,
                                   @Param("userId") Long userId);
}
