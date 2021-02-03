package com.mkitchen.server.repository;
import com.mkitchen.server.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, String> {
}
