package net.java.event_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private int userId;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "user_password",nullable = false)
    private String userPassword;

    @Column(name = "user_email",nullable = false)
    private String userEmail;

    @Column(name = "user_location",nullable = false)
    private String userLocation;

    @Column(name = "user_interests",nullable = false)
    private String userInterests;

    @Column(name = "user_birth",nullable = false)
    private Date userBirth;

    @Column(name = "user_gender",nullable = false)
    private String userGender;

    @Column(name = "user_number",nullable = false)
    private String userNumber;

    @Column(name = "user_photo",nullable = false)
    private String userPhoto;




}
