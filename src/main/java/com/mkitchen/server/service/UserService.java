package com.mkitchen.server.service;

import com.mkitchen.server.entity.User;
import com.mkitchen.server.repository.RoleRepository;
import com.mkitchen.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> save(String username, String email, String password) {

        // check if database has existing user
        Optional<User> userDb = userRepository.findByName(username);
        if (userDb.isEmpty()) {
            User user = User.builder().userName(username)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .enabled(true)
                    .roles(Arrays.asList(roleRepository.findById(1).orElse(null)).stream().collect(Collectors.toSet()))
                    .build();
            userRepository.save(user);
            return ResponseEntity.ok(String.format("User [%s] successfully registered.", username));
        }
        return ResponseEntity.badRequest().body(String.format("User [%s] already exists.", username));
    }
}
