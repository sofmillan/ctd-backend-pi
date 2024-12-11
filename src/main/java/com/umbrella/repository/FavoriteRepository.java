package com.umbrella.repository;

import com.umbrella.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(Integer userId);
    Optional<Favorite> findByUserIdAndEventId(Integer userId, Integer eventId);
    List<Favorite> findByEventId(Integer eventId);
}
