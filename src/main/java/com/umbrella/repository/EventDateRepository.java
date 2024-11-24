package com.umbrella.repository;

import com.umbrella.entity.EventDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDateRepository extends JpaRepository<EventDate, Integer> {
}
