package com.mkitchen.server.repository;

import com.mkitchen.server.entity.AmazonUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AmazonUrlRepository extends JpaRepository<AmazonUrl, String> {

    @Query("SELECT a FROM AmazonUrl a WHERE a.ingredientName = ?1")
    AmazonUrl findByName(String name);
}
