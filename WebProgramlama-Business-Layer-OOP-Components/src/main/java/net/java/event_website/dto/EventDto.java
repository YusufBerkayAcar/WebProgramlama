package net.java.event_website.dto;

import lombok.*;
import net.java.event_website.entity.Event;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDto {

    private int eventId;
    private String eventName;
    private String eventExplanation;
    private Date eventDate;
    private String eventTime;
    private int eventDuration;
    private double eventLocationX;
    private double eventLocationY;
    private String eventCategory;
    private int eventOwnerId;
    private String isAllowed;

    public EventDto(EventDto eventDto){

    }

    public EventDto(Event event) {

    }
}
