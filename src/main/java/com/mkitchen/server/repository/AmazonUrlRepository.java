package com.mkitchen.server.repository;

import com.mkitchen.server.entity.AmazonUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmazonUrlRepository extends JpaRepository<AmazonUrl, String> {
}
