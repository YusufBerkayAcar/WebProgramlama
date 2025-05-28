package net.java.event_website.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "points")
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "point")
    private int point;

    @Column(name = "point_date")
    private LocalDateTime pointDate;

}
