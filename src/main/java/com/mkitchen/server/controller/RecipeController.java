package com.mkitchen.server.controller;
import com.mkitchen.server.dto.SingleRecipeResponse;
import com.mkitchen.server.entity.AmazonUrl;
import com.mkitchen.server.entity.Recipe;
import com.mkitchen.server.service.RecipeService;
import com.mkitchen.server.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UrlService urlService;

    @PostMapping("/postRecipe")
    private Recipe addRecipe(@RequestBody Recipe recipe) {
        System.out.println("Recipe Received: " + recipe);
        return recipeService.save(recipe);
    }

    @GetMapping("/getAllRecipes")
    private List<Recipe> getAllRecipes() {
        return recipeService.getAll();
    }

    @GetMapping("/getRecipeById/{id}")
    private SingleRecipeResponse getRecipeById(@PathVariable Integer id) {
        return recipeService.getById(id);
    }

    @PostMapping("/postUrl")
    private AmazonUrl saveUrl(@RequestBody AmazonUrl request) {
        return urlService.save(request);
    }
}
