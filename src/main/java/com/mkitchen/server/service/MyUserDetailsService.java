package com.mkitchen.server.service;

import com.mkitchen.server.entity.User;
import com.mkitchen.server.repository.UserRepository;
import com.mkitchen.server.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("No user found: " + userName));
        return user.map(MyUserDetails::new).get();
    }
}
