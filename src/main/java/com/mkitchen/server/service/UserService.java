package com.mkitchen.server.service;

import com.mkitchen.server.entity.User;
import com.mkitchen.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> save(String username, String password) {

        // check if database has existing user
        User userDb = userRepository.findByName(username);
        if (userDb == null) {
            User user = User.builder().userName(username)
                    .password(password)
                    .enabled(true)
                    .role("user").build();
            System.out.println(userRepository.save(user));
            return ResponseEntity.ok(String.format("User [%s] successfully registered. ", username));
        }
        return ResponseEntity.badRequest().body(String.format("User [%s] already exist.", username));
    }
}
