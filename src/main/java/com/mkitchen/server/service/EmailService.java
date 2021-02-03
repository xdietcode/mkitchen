package com.mkitchen.server.service;

import com.mkitchen.server.entity.Email;
import com.mkitchen.server.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;
    public Email subscribe(Email email) {
        return emailRepository.save(email);
    }
}
