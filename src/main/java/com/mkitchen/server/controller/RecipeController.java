package com.mkitchen.server.controller;
import com.mkitchen.server.dto.SingleRecipeResponse;
import com.mkitchen.server.entity.AmazonUrl;
import com.mkitchen.server.entity.Email;
import com.mkitchen.server.entity.Recipe;
import com.mkitchen.server.model.SimplifiedRecipe;
import com.mkitchen.server.service.EmailService;
import com.mkitchen.server.service.RecipeService;
import com.mkitchen.server.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UrlService urlService;

    @PostMapping("/post/recipe")
    private Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.save(recipe);
    }

    @GetMapping("/recipes/all")
    private List<SimplifiedRecipe> getAllRecipes() {
        return recipeService.getAll();
    }

    @GetMapping("/recipes/id/{id}")
    private SingleRecipeResponse getRecipeById(@PathVariable Integer id) {
        return recipeService.getById(id);
    }

    @GetMapping("/recipes/name/{recipeName}")
    private List<SimplifiedRecipe> getRecipesByName(@PathVariable String recipeName) {return recipeService.getByName(recipeName);}

    @PostMapping("/post/urls")
    private List<AmazonUrl> saveUrls(@RequestBody List<AmazonUrl> aUrls) {
        return urlService.saveAll(aUrls);
    }

    @GetMapping("/recipes/cat/{cat}/{subCat}")
    private List<SimplifiedRecipe> getRecipesByCat(@PathVariable String cat, @PathVariable String subCat) {
        return recipeService.getByCat(cat, subCat);
    }

}
