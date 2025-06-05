package net.java.event_website.mapper;

import net.java.event_website.dto.EventDto;
import net.java.event_website.entity.Event;

public class EventMapper {

    public static EventDto mapToEventDto(Event event) {
        return new EventDto(
                event.getEventId(),
                event.getEventName(),
                event.getEventExplanation(),
                event.getEventDate(),
                event.getEventTime(),
                event.getEventDuration(),
                event.getEventLocationX(),
                event.getEventLocationY(),
                event.getEventCategory(),
                event.getEventOwnerId(),
                event.getIsAllowed()
        );
    }

    public static Event mapToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setEventId(eventDto.getEventId());
        event.setEventName(eventDto.getEventName());
        event.setEventExplanation(eventDto.getEventExplanation());
        event.setEventDate(eventDto.getEventDate());
        event.setEventTime(eventDto.getEventTime());
        event.setEventDuration(eventDto.getEventDuration());
        event.setEventLocationX(eventDto.getEventLocationX());
        event.setEventLocationY(eventDto.getEventLocationY());
        event.setEventCategory(eventDto.getEventCategory());
        event.setEventOwnerId(eventDto.getEventOwnerId());
        event.setIsAllowed(eventDto.getIsAllowed());
        return event;
    }

}
