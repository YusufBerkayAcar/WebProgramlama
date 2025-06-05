package net.java.event_website.service.impl;

import lombok.AllArgsConstructor;
import net.java.event_website.dto.ParticipantsDto;
import net.java.event_website.entity.Event;
import net.java.event_website.entity.Participants;
import net.java.event_website.mapper.ParticipantsMapper;
import net.java.event_website.repository.EventRepository;
import net.java.event_website.repository.ParticipantsRepository;
import net.java.event_website.service.ParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ParticipantsServiceImpl implements ParticipantsService {

    @Autowired
    private ParticipantsRepository participantsRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public ParticipantsDto joinEvent(ParticipantsDto participantsDto) {
        Participants participants = ParticipantsMapper.mapToParticipants(participantsDto);
        Participants joinedEvent = participantsRepository.save(participants);
        return ParticipantsMapper.mapToParticipantsDto(joinedEvent);
    }

    @Override
    public boolean checkEventConflict(int userId, Event newEvent) {

        List<Event> userEvents = participantsRepository.findEventsByUserId(userId);


        Date newEventStartDateTime = calculateEventStartDateTime(newEvent);
        Date newEventEndDateTime = calculateEventEndDateTime(newEvent);


        for (Event userEvent : userEvents) {
            Date userEventStartDateTime = calculateEventStartDateTime(userEvent);
            Date userEventEndDateTime = calculateEventEndDateTime(userEvent);

            if (eventsOverlap(newEventStartDateTime, newEventEndDateTime, userEventStartDateTime, userEventEndDateTime)) {
                return true;
            }
        }
        return false;
    }


    private Date calculateEventStartDateTime(Event event) {
        String eventDateString = new SimpleDateFormat("yyyy-MM-dd").format(event.getEventDate());
        String eventDateTimeString = eventDateString + " " + event.getEventTime();

        try {
            System.out.println("Calculating start date for: " + eventDateTimeString);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(eventDateTimeString);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid event date or time format", e);
        }
    }




    private Date calculateEventEndDateTime(Event event) {
        Date startDateTime = calculateEventStartDateTime(event);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDateTime);
        calendar.add(Calendar.MINUTE, event.getEventDuration());
        return calendar.getTime();
    }


    private boolean eventsOverlap(Date start1, Date end1, Date start2, Date end2) {
        System.out.println("Checking overlap between: ");
        System.out.println("Event 1: Start=" + start1 + ", End=" + end1);
        System.out.println("Event 2: Start=" + start2 + ", End=" + end2);
        return start1.before(end2) && start2.before(end1);
    }




    @Override
    public List<Event> getEventsByUserId(int userId) {
        return participantsRepository.findEventsByUserId(userId);
    }

    @Override
    public void leaveEvent(int userId, int eventId) {
        participantsRepository.deleteByUserIdAndEventId(userId, eventId);
    }


}
