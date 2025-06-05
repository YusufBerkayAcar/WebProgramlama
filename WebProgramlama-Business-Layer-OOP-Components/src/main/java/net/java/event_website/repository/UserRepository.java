package net.java.event_website.repository;

import net.java.event_website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String username);
    User findByUserId(int userId);

}
