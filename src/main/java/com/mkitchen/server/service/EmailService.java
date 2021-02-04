package com.mkitchen.server.service;

import com.mkitchen.server.entity.Email;
import com.mkitchen.server.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;
    public Email subscribe(Email email) {
        Email res = new Email();
        try{
            res = emailRepository.save(email);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Email already exists!");
        }
        return res;
    }
}
