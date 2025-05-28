package net.java.event_website.mapper;

import net.java.event_website.dto.UserDto;
import net.java.event_website.entity.User;

public class UserMapper {


    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getUserPassword(),
                user.getUserEmail(),
                user.getUserLocation(),
                user.getUserPhoto(),
                user.getUserBirth(),
                user.getUserInterests(),
                user.getUserNumber(),
                user.getUserGender()
        );
    }


    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setUserPassword(userDto.getUserPassword());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserLocation(userDto.getUserLocation());
        user.setUserInterests(userDto.getUserInterests());
        user.setUserBirth(userDto.getUserBirth());
        user.setUserGender(userDto.getUserGender());
        user.setUserNumber(userDto.getUserNumber());
        user.setUserPhoto(userDto.getUserPhoto());
        return user;
    }
}
