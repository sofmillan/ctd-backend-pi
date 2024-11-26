package com.umbrella.repository;

import com.umbrella.entity.EventDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventDateRepository extends JpaRepository<EventDate, Integer> {
    List<EventDate> findByEventId(Integer id);
    List<EventDate> findByEventDateBetween(LocalDate startDate, LocalDate endDate);
}
