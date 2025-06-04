package net.java.event_website.service;

import net.java.event_website.dto.EventDto;
import net.java.event_website.dto.UserDto;

import java.util.List;

public interface EventService {

    EventDto createEvent(EventDto eventDto);
    List<EventDto> getAllEventsByOwnerId(int eventOwnerId);
    List<EventDto> getAllEventsByOwnerId2(int eventOwnerId);
    EventDto updateAllowStatus(int eventId, String isAllowed);
    void deleteEvent(int eventId);
    EventDto findEvent(int eventId);
    EventDto updateEvent(EventDto eventDto, int eventId);
    List<EventDto> getAllEvents();
    void participateInEvent(int userId, int eventId);

}
