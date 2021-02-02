package com.mkitchen.server.repository;

import com.mkitchen.server.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(value = "SELECT r FROM Recipe r")
    List<Recipe> getAllRecipes();

    @Query("SELECT r FROM Recipe r INNER JOIN r.subCategories s WHERE s.id = ?1")
    List<Recipe> getRecipesByCat(Integer subCatId);
}
