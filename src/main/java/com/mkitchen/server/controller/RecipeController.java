package com.mkitchen.server.controller;

import com.mkitchen.server.dto.CreateRecipeRequest;
import com.mkitchen.server.dto.SingleRecipeResponse;
import com.mkitchen.server.entity.AmazonUrl;
import com.mkitchen.server.entity.Ingredient;
import com.mkitchen.server.entity.IngredientWrapper;
import com.mkitchen.server.entity.Recipe;
import com.mkitchen.server.repository.AmazonUrlRepository;
import com.mkitchen.server.repository.IngredientRepository;
import com.mkitchen.server.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private AmazonUrlRepository urlRepository;

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


    @GetMapping("/getRecipeById/{id}")
    private SingleRecipeResponse getRecipeById(@PathVariable Integer id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        List<IngredientWrapper> wrappers = new ArrayList<>();
        for (Ingredient in : recipe.getIngredients()) {
            AmazonUrl url = urlRepository.findByName(in.getName());
            IngredientWrapper wrapped = IngredientWrapper.builder()
                    .name(in.getName())
                    .amount(in.getAmount())
                    .unit(in.getUnit())
                    .url(url == null ? "" : url.getUrl())
                    .build();
            wrappers.add(wrapped);
        }


        SingleRecipeResponse res = SingleRecipeResponse.builder()
                .name(recipe.getName())
                .caloriesPerServing(recipe.getCaloriesPerServing())
                .imageUrl(recipe.getImageUrl())
                .instruction(recipe.getInstruction())
                .prepTime(recipe.getPrepTime())
                .servings(recipe.getServings())
                .ingredient(wrappers)
                .build();

        return res;
    }

    @PostMapping("/postUrl")
    private AmazonUrl addRecipe(@RequestBody AmazonUrl request) {
        return urlRepository.save(request);
    }
}
