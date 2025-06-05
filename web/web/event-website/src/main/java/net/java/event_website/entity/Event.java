package net.java.event_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id",nullable = false)
    private int eventId;

    @Column(name = "event_name",nullable = false)
    private String eventName;

    @Column(name = "event_explanations",nullable = false)
    private String eventExplanation;

    @Column(name = "event_date",nullable = false)
    private Date eventDate;

    @Column(name = "event_time",nullable = false)
    private String eventTime;

    @Column(name = "event_duration",nullable = false)
    private int eventDuration;

    @Column(name = "event_location_x",nullable = false)
    private double eventLocationX;

    @Column(name = "event_location_y",nullable = false)
    private double eventLocationY;

    @Column(name = "event_category",nullable = false)
    private String eventCategory;

    @Column(name = "event_owner_id",nullable = false)
    private int eventOwnerId;

    @Column(name = "is_allowed",nullable = true)
    private String isAllowed;

}
