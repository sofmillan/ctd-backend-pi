package com.umbrella.repository;

import com.umbrella.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Galleryrepository extends JpaRepository<Gallery, Integer> {
}
