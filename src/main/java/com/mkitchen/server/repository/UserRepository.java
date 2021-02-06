package com.mkitchen.server.repository;

import com.mkitchen.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    Optional<User> findByName(String name);
}
