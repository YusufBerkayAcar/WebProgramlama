package net.java.event_website.service;

import net.java.event_website.entity.Points;

import java.util.List;

public interface PointsService {
    void addPoint(int userId, int point);
    List<Points> getPointsByUserId(int userId);
    int calculateTotalPoints(int userId);

}
