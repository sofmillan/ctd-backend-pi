package com.umbrella.repository;

import com.umbrella.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
    List<Gallery> findByEventId(Integer eventId);
}
