package net.java.event_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.java.event_website.ParticipantId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participants", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"}))
@IdClass(ParticipantId.class)
public class Participants {

    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Id
    @Column(name = "event_id", nullable = false)
    private int eventId;

}
