package net.java.event_website.controller;

import net.java.event_website.dto.MessageDto;
import net.java.event_website.entity.Message;
import net.java.event_website.mapper.MessageMapper;
import net.java.event_website.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;


    @GetMapping("/event/{eventId}")
    public List<MessageDto> getMessagesByEventId(@PathVariable int eventId) {
        List<Message> messages = messageService.getMessagesByEventId(eventId);
        return messageMapper.toDtoList(messages);
    }


    @PostMapping("/add")
    public MessageDto addMessage(@RequestBody MessageDto messageDto) {
        Message message = messageMapper.toEntity(messageDto);
        Message savedMessage = messageService.saveMessage(message);
        return messageMapper.toDto(savedMessage);
    }
}
