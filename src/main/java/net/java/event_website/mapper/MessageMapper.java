package net.java.event_website.mapper;

import net.java.event_website.dto.MessageDto;
import net.java.event_website.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {


    public MessageDto toDto(Message message) {
        return new MessageDto(
                message.getMessageId(),
                message.getUserId(),
                message.getEventId(),
                message.getMessage(),
                message.getUserName()
        );
    }


    public Message toEntity(MessageDto messageDto) {
        Message message = new Message();
        message.setMessageId(messageDto.getMessageId());
        message.setUserId(messageDto.getUserId());
        message.setEventId(messageDto.getEventId());
        message.setMessage(messageDto.getMessage());
        message.setUserName(messageDto.getUserName());

        return message;
    }


    public List<MessageDto> toDtoList(List<Message> messages) {
        return messages.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public List<Message> toEntityList(List<MessageDto> messageDtos) {
        return messageDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
