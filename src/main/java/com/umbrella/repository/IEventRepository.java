package com.umbrella.repository;

import com.umbrella.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event, Integer> {
}
