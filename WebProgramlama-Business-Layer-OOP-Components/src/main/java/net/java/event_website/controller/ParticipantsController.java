package net.java.event_website.controller;

import net.java.event_website.entity.Event;
import lombok.AllArgsConstructor;
import net.java.event_website.dto.ParticipantsDto;
import net.java.event_website.entity.Participants;
import net.java.event_website.repository.EventRepository;
import net.java.event_website.repository.ParticipantsRepository;
import net.java.event_website.service.ParticipantsService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
@RestController
@RequestMapping("/api/participants")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ParticipantsController {

    private ParticipantsService participantsService;
    private final ParticipantsRepository participantsRepository;
    private final EventRepository eventRepository;

    @PostMapping("/joinEvent")
    public ResponseEntity<?> joinEvent(@RequestBody ParticipantsDto participantsDto) {
        int userId = participantsDto.getUserId();
        int eventId = participantsDto.getEventId();


        Event newEvent = eventRepository.findById(eventId).orElseThrow(() ->
                new RuntimeException("Event not found"));


        if (participantsService.checkEventConflict(userId, newEvent)) {
            return new ResponseEntity<>("Event timing conflicts with an already joined event.", HttpStatus.CONFLICT);
        }


        ParticipantsDto joinedEvent = participantsService.joinEvent(participantsDto);
        return new ResponseEntity<>(joinedEvent, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}/events")
    public ResponseEntity<List<Event>> getEventsByUserId(@PathVariable int userId) {
        List<Event> events = participantsService.getEventsByUserId(userId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/event/{eventId}")
    public ResponseEntity<String> leaveEvent(@PathVariable int userId, @PathVariable int eventId) {
        participantsService.leaveEvent(userId, eventId);
        return new ResponseEntity<>("Event left successfully.", HttpStatus.OK);
    }

}
