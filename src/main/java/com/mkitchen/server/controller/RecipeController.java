package com.mkitchen.server.controller;

import com.mkitchen.server.dto.CreateRecipeRequest;
import com.mkitchen.server.entity.Ingredient;
import com.mkitchen.server.entity.Recipe;
import com.mkitchen.server.repository.IngredientRepository;
import com.mkitchen.server.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @PostMapping("/postRecipe")
    private Recipe addRecipe(@RequestBody CreateRecipeRequest request) {
        List<Ingredient> ingredientsSaved = ingredientRepository.saveAll(request.getRecipe().getIngredients());
        request.getRecipe().setIngredients(ingredientsSaved.stream().collect(Collectors.toSet()));

        return recipeRepository.save(request.getRecipe());
    }

    @GetMapping("/getAllRecipes")
    private List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }
}
