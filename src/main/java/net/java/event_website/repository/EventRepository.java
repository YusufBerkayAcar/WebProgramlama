package net.java.event_website.repository;

import net.java.event_website.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findByEventOwnerId(int ownerId);
    Event findByEventId(int eventId);
    List<Event> findByIsAllowed(String isAllowed);
}
