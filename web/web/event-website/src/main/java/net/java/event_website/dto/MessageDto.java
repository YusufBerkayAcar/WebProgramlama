package net.java.event_website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private int messageId;
    private int userId;
    private int eventId;
    private String message;
    private String userName;
}
