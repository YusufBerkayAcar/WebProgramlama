package net.java.event_website.service.impl;

import lombok.AllArgsConstructor;
import net.java.event_website.entity.Points;
import net.java.event_website.repository.PointsRepository;
import net.java.event_website.service.PointsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PointsServiceImpl implements PointsService {

    private final PointsRepository pointsRepository;

    @Override
    public void addPoint(int userId, int point) {
        Points newPoint = new Points();
        newPoint.setUserId(userId);
        newPoint.setPoint(point);
        newPoint.setPointDate(LocalDateTime.now());
        pointsRepository.save(newPoint);
    }

    @Override
    public List<Points> getPointsByUserId(int userId) {
        return pointsRepository.findByUserId(userId);
    }

    @Override
    public int calculateTotalPoints(int userId) {
        List<Points> userPoints = pointsRepository.findByUserId(userId);

        if (userPoints == null || userPoints.isEmpty()) {
            return 0;
        }

        return userPoints.stream().mapToInt(Points::getPoint).sum();
    }


}
