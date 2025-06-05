package net.java.event_website.mapper;

import net.java.event_website.dto.ParticipantsDto;
import net.java.event_website.entity.Participants;

public class ParticipantsMapper {

    public static ParticipantsDto mapToParticipantsDto(Participants participants){

        return new ParticipantsDto(
                participants.getUserId(),
                participants.getEventId()
        );

    }

    public static Participants mapToParticipants(ParticipantsDto participantsDto){
        Participants participants = new Participants();
        participants.setUserId(participantsDto.getUserId());
        participants.setEventId(participantsDto.getEventId());

        return participants;
    }

}
