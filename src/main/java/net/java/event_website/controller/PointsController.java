package net.java.event_website.controller;

import lombok.AllArgsConstructor;
import net.java.event_website.dto.PointsDto;
import net.java.event_website.entity.Points;
import net.java.event_website.mapper.PointsMapper;
import net.java.event_website.service.PointsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/points")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PointsController {

    private final PointsService pointsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PointsDto>> getUserPoints(@PathVariable int userId) {
        List<Points> points = pointsService.getPointsByUserId(userId);
        List<PointsDto> pointsDtos = points.stream()
                .map(PointsMapper::mapToPointsDto)
                .toList();

        return ResponseEntity.ok(pointsDtos);
    }

    @GetMapping("/user/{userId}/total")
    public ResponseEntity<Integer> getUserTotalPoints(@PathVariable int userId) {
        int totalPoints = pointsService.calculateTotalPoints(userId);
        return ResponseEntity.ok(totalPoints);
    }

    @PostMapping("/addPoint")
    public ResponseEntity<String> addPoint(@RequestBody PointsDto pointsDto) {
        try {

            List<Points> userPoints = pointsService.getPointsByUserId(pointsDto.getUserId());

            boolean isFirstEvent = userPoints.isEmpty();


            int pointsToAdd = isFirstEvent ? 20 : 10;

            pointsService.addPoint(pointsDto.getUserId(), pointsToAdd);
            return ResponseEntity.ok("Point added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding point: " + e.getMessage());
        }
    }
}
