package net.java.event_website.service;

import net.java.event_website.dto.ParticipantsDto;
import net.java.event_website.entity.Event;

import java.util.List;

public interface ParticipantsService {

    ParticipantsDto joinEvent(ParticipantsDto participantsDto);

    List<Event> getEventsByUserId(int userId);

    void leaveEvent(int userId, int eventId);

    boolean checkEventConflict(int userId, Event newEvent);



}
