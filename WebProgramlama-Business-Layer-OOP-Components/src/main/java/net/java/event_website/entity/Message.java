package net.java.event_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id",nullable = false)
    private int messageId;

    @Column(name = "user_id",nullable = false)
    private int userId;

    @Column(name = "event_id",nullable = false)
    private int eventId;

    @Column(name = "message",nullable = false)
    private String message;

    @Column(name= "user_name",nullable = false)
    private String userName;



}
