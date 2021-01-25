package com.mkitchen.server.repository;

import com.mkitchen.server.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(value = "SELECT r FROM Recipe r")
    public List<Recipe> getAllRecipes();
}
