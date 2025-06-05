package net.java.event_website.service;

import net.java.event_website.dto.UserDto;

import java.util.Date;
import java.util.List;


public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto login(String username , String password);
    UserDto updatePassword(String userName, String userEmail, String newPassword);
    UserDto updateUser(int userId, UserDto userDto);
    List<UserDto> getAllUsers();
    void deleteUser(int userId);

}
