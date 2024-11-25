package com.umbrella.repository;

import com.umbrella.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {
    Optional<Event> findByName(String name);
    @Query("Select distinct e.city from Event e")
    List<String> citiesOfEvent();
    List<Event> findByNameContains(String eventName);
}
