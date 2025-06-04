package net.java.event_website.repository;

import net.java.event_website.entity.Points;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointsRepository extends JpaRepository<Points, Integer> {
    List<Points> findByUserId(int userId);
}
