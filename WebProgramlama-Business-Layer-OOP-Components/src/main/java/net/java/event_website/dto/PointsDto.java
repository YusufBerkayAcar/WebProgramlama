package net.java.event_website.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointsDto {
    private int id;
    private int userId;
    private int point;
    private LocalDateTime pointDate;
}
