package net.java.event_website.service.impl;

import lombok.AllArgsConstructor;
import net.java.event_website.entity.Message;
import net.java.event_website.repository.MessageRepository;
import net.java.event_website.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesByEventId(int eventId) {
        return messageRepository.findByEventId(eventId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
