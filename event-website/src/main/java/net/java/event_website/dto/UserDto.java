package net.java.event_website.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.java.event_website.entity.User;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userLocation;
    private String userInterests;
    private Date userBirth;
    private String userGender;
    private String userNumber;
    private String userPhoto;



    public UserDto(User user) {

    }




}
