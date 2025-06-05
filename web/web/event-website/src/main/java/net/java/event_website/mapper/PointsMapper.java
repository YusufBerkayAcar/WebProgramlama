package net.java.event_website.mapper;

import net.java.event_website.dto.PointsDto;
import net.java.event_website.entity.Points;

public class PointsMapper {


    public static PointsDto mapToPointsDto(Points points) {
        PointsDto pointsDto = new PointsDto();
        pointsDto.setId(points.getId());
        pointsDto.setUserId(points.getUserId());
        pointsDto.setPoint(points.getPoint());
        pointsDto.setPointDate(points.getPointDate());
        return pointsDto;
    }


    public static Points mapToPoints(PointsDto pointsDto) {
        Points points = new Points();
        points.setId(pointsDto.getId());
        points.setUserId(pointsDto.getUserId());
        points.setPoint(pointsDto.getPoint());
        points.setPointDate(pointsDto.getPointDate());
        return points;
    }
}
