package com.mkitchen.server.repository;

import com.mkitchen.server.entity.Recipe;
import com.mkitchen.server.entity.SimplifiedRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(value = "SELECT r FROM Recipe r")
    public List<Recipe> getAllRecipes();
    @Query("SELECT r FROM Recipe r WHERE r.name like %?1%")
    public List<Recipe> findByName(String name);
}
