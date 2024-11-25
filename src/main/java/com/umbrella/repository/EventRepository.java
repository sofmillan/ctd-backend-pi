package com.umbrella.repository;

import com.umbrella.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {
    @Query("Select distinct e.city from Event e")
    List<String> citiesOfEvent();
    List<Event> findByNameContains(String eventName);
}
