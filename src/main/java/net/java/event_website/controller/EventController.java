package net.java.event_website.controller;

import lombok.AllArgsConstructor;
import net.java.event_website.dto.EventDto;
import net.java.event_website.dto.UserDto;
import net.java.event_website.repository.EventRepository;
import net.java.event_website.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EventController {

    private EventService eventService;
    private final EventRepository eventRepository;

    @PostMapping("/createEvent")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto){
        System.out.println("Received Event DTO: " + eventDto);
        EventDto savedEvent = eventService.createEvent(eventDto);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @GetMapping("/getAllEventsByOwnerId/{eventOwnerId}")
    public ResponseEntity<List<EventDto>> getAllEventsByOwnerId(@PathVariable("eventOwnerId") int eventOwnerId){

        List<EventDto> events = eventService.getAllEventsByOwnerId(eventOwnerId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/getAllEventsByOwnerId2/{eventOwnerId}")
    public ResponseEntity<List<EventDto>> getAllEventsByOwnerId2(@PathVariable("eventOwnerId") int eventOwnerId){

        List<EventDto> events = eventService.getAllEventsByOwnerId2(eventOwnerId);
        return ResponseEntity.ok(events);
    }

    @PutMapping("/isAllowed/{eventId}")
    public ResponseEntity<EventDto> isAllowed(@PathVariable("eventId") int eventId,
                                              @RequestBody Map<String,String> requestBody){
        String isAllowed = requestBody.get("isAllowed");
        EventDto eventDto = eventService.updateAllowStatus(eventId,isAllowed);
        if(eventDto != null){
            return ResponseEntity.ok(eventDto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable("eventId") int eventId){

        eventService.deleteEvent(eventId);

        return ResponseEntity.ok("Event deleted by id : "+eventId);

    }

    @GetMapping("/getEvent/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable("eventId") int eventId){
        EventDto eventDto = eventService.findEvent(eventId);
        return ResponseEntity.ok(eventDto);
    }

    @PutMapping("/updateEvent/{eventId}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable("eventId") int eventId,
                                                @RequestBody EventDto eventDto) {
        EventDto updatedEvent = eventService.updateEvent(eventDto,eventId);

        if(updatedEvent != null){
            return ResponseEntity.ok(updatedEvent);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<EventDto>> getAllEvents(){
        List<EventDto> events =eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }



}
