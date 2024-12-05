package com.umbrella.repository;

import com.umbrella.entity.EventFeature;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventFeatureRepository extends JpaRepository<EventFeature, Integer>{
    List<EventFeature> findByEventId(Integer eventId);
    @Transactional
    @Modifying
    @Query("DELETE FROM EventFeature ef WHERE ef.feature.id = :featureId")
    void deleteByFeatureId(@Param("featureId") Integer featureId);
}
