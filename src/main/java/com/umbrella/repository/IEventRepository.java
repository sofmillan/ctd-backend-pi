package com.umbrella.repository;

import com.umbrella.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findByName(String name);
}
