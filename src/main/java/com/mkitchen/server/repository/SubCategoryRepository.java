package com.mkitchen.server.repository;

import com.mkitchen.server.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    @Query("SELECT s FROM Category c INNER JOIN c.subCategories s WHERE s.name = ?1 AND c.id = ?2")
    SubCategory findByName(String name, Integer categoryId);

    @Query("SELECT s FROM SubCategory s")
    List<SubCategory> getAllSubCategories();
}
