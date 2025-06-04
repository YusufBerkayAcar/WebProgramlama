package net.java.event_website.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import net.java.event_website.dto.UserDto;
import net.java.event_website.entity.User;
import net.java.event_website.exception.ResourceNotFoundException;
import net.java.event_website.repository.UserRepository;
import net.java.event_website.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    private UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto,
                                       HttpSession session,
                                       HttpServletResponse response) {
        UserDto user = userService.login(userDto.getUserName(), userDto.getUserPassword());

        if (user != null) {
            // Session'a kullanıcıyı kaydet
            session.setAttribute("user", user);

            // Cookie oluştur (örnek: username cookie'si)
            Cookie cookie = new Cookie("username", user.getUserName());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60); // 1 saat
            response.addCookie(cookie);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Kullanıcı adı veya şifre hatalı");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();

        // Cookie sil
        Cookie cookie = new Cookie("username", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok("Oturum kapatıldı");
    }



    @PutMapping("/resetPassword/{username}")
    public ResponseEntity<UserDto> resetPassword(@PathVariable("username") String userName,
                                                 @RequestBody Map<String, String> requestBody){
        String newPassword = requestBody.get("userPassword");
        String userEmail = requestBody.get("userEmail");
        UserDto userDto = userService.updatePassword(userName, userEmail, newPassword);

        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") int userId,
                                              @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userId, userDto);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId){

        userService.deleteUser(userId);

        return  ResponseEntity.ok("User Deleted With Id : " + userId);

    }


}
