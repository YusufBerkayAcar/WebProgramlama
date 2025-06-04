package net.java.event_website.service.impl;

import lombok.AllArgsConstructor;
import net.java.event_website.dto.UserDto;
import net.java.event_website.entity.User;
import net.java.event_website.exception.ResourceNotFoundException;
import net.java.event_website.mapper.UserMapper;
import net.java.event_website.repository.UserRepository;
import net.java.event_website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto login(String username, String password) {
        User user = userRepository.findByUserName(username);

        if (user != null && user.getUserPassword().equals(password)){
            System.out.println("username : "+user.getUserName());
            return new UserDto(user.getUserId(), user.getUserName(), user.getUserPassword(), user.getUserEmail(), user.getUserLocation(), user.getUserPhoto(), user.getUserBirth(), user.getUserInterests(), user.getUserNumber(), user.getUserGender());
        }
        return null;
    }


    public UserDto updatePassword(String userName, String userEmail, String newPassword) {
        User user = userRepository.findByUserName(userName);

        if (user != null) {
            if (user != null && user.getUserEmail().equals(userEmail)) {
                user.setUserPassword(newPassword);
                userRepository.save(user);
                return new UserDto(user);
            }
            else{
                System.out.println("Wrong Email");
            }
        }
        return null;
    }

    public UserDto updateUser(int userId, UserDto userDto) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUserName(userDto.getUserName());
            user.setUserEmail(userDto.getUserEmail());
            user.setUserPassword(userDto.getUserPassword());
            user.setUserLocation(userDto.getUserLocation());
            user.setUserPhoto(userDto.getUserPhoto());
            user.setUserNumber(userDto.getUserNumber());
            user.setUserBirth(userDto.getUserBirth());

            userRepository.save(user);
            return new UserDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exists with given id : " + userId));



        userRepository.deleteById(userId);
    }


}
