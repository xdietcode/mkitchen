package com.mkitchen.server.controller;

import com.mkitchen.server.dto.RegisterRequest;
import com.mkitchen.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private ResponseEntity<?> saveUser(@RequestBody RegisterRequest request) {
        return userService.save(request.getUsername(), request.getPassword());
    }
}
