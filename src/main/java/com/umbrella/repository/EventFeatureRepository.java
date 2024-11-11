package com.umbrella.repository;

import com.umbrella.entity.EventFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventFeatureRepository extends JpaRepository<EventFeature, Integer>{
    List<EventFeature> findByEventId(Integer eventId);
}
