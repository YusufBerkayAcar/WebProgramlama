package net.java.event_website.dto;

import lombok.*;
import net.java.event_website.entity.Participants;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ParticipantsDto {

    private int userId;
    private int eventId;


    public ParticipantsDto(Participants participants){

    }

    public ParticipantsDto(int userId, int eventId) {
    }
}
