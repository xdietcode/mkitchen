package com.mkitchen.server.controller;

import com.mkitchen.server.dto.AuthRequest;
import com.mkitchen.server.dto.AuthResponse;
import com.mkitchen.server.dto.RegisterRequest;
import com.mkitchen.server.service.MyUserDetailsService;
import com.mkitchen.server.service.UserService;
import com.mkitchen.server.utils.JwtUtil;
import com.mkitchen.server.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    private ResponseEntity<?> saveUser(@RequestBody RegisterRequest request) {
        return userService.save(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        }


        final MyUserDetails userDetails = (MyUserDetails) userDetailsService
                .loadUserByUsername(request.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
