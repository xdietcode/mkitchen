package com.mkitchen.server.repository;

import com.mkitchen.server.entity.Ingredient;
import com.mkitchen.server.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("SELECT i FROM Recipe r INNER JOIN  r.ingredients i " +
            "WHERE r.id = ?1 ORDER BY i.unit DESC, i.amount DESC, i.name")
    List<Ingredient> getIngredientsByRecipeId(Integer recipeId);
}
