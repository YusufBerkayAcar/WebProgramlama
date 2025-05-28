package net.java.event_website.service;

import net.java.event_website.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    public List<Message> getMessagesByEventId(int eventId);

    public Message saveMessage(Message message);


}
