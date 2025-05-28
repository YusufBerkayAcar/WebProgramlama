package net.java.event_website.repository;

import jakarta.transaction.Transactional;
import net.java.event_website.entity.Event;
import net.java.event_website.entity.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantsRepository extends JpaRepository<Participants,Integer> {

    @Query("SELECT e FROM Event e JOIN Participants p ON e.eventId = p.eventId WHERE p.userId = :userId")
    List<Event> findEventsByUserId(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Participants p WHERE p.userId = :userId AND p.eventId = :eventId")
    void deleteByUserIdAndEventId(@Param("userId") int userId, @Param("eventId") int eventId);


}
