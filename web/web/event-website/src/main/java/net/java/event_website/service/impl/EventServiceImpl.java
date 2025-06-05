package net.java.event_website.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import net.java.event_website.dto.EventDto;
import net.java.event_website.dto.UserDto;
import net.java.event_website.entity.Event;
import net.java.event_website.exception.ResourceNotFoundException;
import net.java.event_website.mapper.UserMapper;
import net.java.event_website.repository.EventRepository;
import net.java.event_website.repository.PointsRepository;
import net.java.event_website.service.EventService;
import net.java.event_website.mapper.EventMapper;
import net.java.event_website.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PointsRepository pointsRepository;

    @Autowired
    private PointsService pointsService;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = EventMapper.mapToEvent(eventDto);
        Event savedEvent = eventRepository.save(event);

        pointsService.addPoint(eventDto.getEventOwnerId(), 15);

        return EventMapper.mapToEventDto(savedEvent);
    }

    @Override
    public List<EventDto> getAllEventsByOwnerId(int eventOwnerId) {
        List<Event> events = eventRepository.findByEventOwnerId(eventOwnerId);


        return events.stream()
                .map(EventMapper::mapToEventDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<EventDto> getAllEventsByOwnerId2(int eventOwnerId) {
        List<Event> events = eventRepository.findByEventOwnerId(eventOwnerId);


        return events.stream()
                .filter(event -> "accept".equalsIgnoreCase(event.getIsAllowed()))
                .map(EventMapper::mapToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto updateAllowStatus(int eventId, String isAllowed) {
        Event event = eventRepository.findByEventId(eventId);
        if(event != null){
            event.setIsAllowed(isAllowed);
            eventRepository.save(event);
            return new EventDto(event);
        }
        else{
            System.out.println("Wrong Event Id");
        }
        return null;
    }

    @Override
    public void deleteEvent(int eventId) {

        Event event = eventRepository.findByEventId(eventId);

        if (event == null){
            System.out.println("This event is not exist");
        }
        else {
            eventRepository.deleteById(eventId);
        }


    }

    @Override
    public EventDto findEvent(int eventId) {
        Event event = eventRepository.findByEventId(eventId);
        if (event == null) {
            throw new EntityNotFoundException("Event not found with ID: " + eventId);
        }
        return EventMapper.mapToEventDto(event);
    }

    @Override
    public EventDto updateEvent(EventDto eventDto, int eventId) {
        Optional<Event> existingEvent = Optional.ofNullable(eventRepository.findByEventId(eventId));
        if(existingEvent.isPresent()){
            Event event = existingEvent.get();
            event.setEventName(eventDto.getEventName());
            event.setEventExplanation(eventDto.getEventExplanation());
            event.setEventDate(eventDto.getEventDate());
            event.setEventTime(eventDto.getEventTime());
            event.setEventDuration(eventDto.getEventDuration());
            event.setEventCategory(eventDto.getEventCategory());
            event.setEventLocationX(eventDto.getEventLocationX());
            event.setEventLocationY(eventDto.getEventLocationY());
            eventRepository.save(event);
            return new EventDto(event);
        }
        return null;

    }

    @Override
    public List<EventDto> getAllEvents() {

        List<Event> events = eventRepository.findByIsAllowed("accept");

        return events.stream()
                .map(EventMapper::mapToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public void participateInEvent(int userId, int eventId) {



        boolean isFirstParticipation = pointsRepository.findByUserId(userId).isEmpty();
        if (isFirstParticipation) {
            pointsService.addPoint(userId, 20); // İlk katılım bonusu
        }


        pointsService.addPoint(userId, 10);
    }

}
