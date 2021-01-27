package com.mkitchen.server.service;

import com.mkitchen.server.dto.SingleRecipeResponse;
import com.mkitchen.server.entity.*;
import com.mkitchen.server.repository.IngredientRepository;
import com.mkitchen.server.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UrlService urlService;

    /**
     *
     * @param recipe recipe obj
     * @return recipe after saved into database
     */
    public Recipe save(Recipe recipe) {
        Set<Ingredient> ingredients = recipe.getIngredients();
        ingredientRepository.saveAll(ingredients);
        recipe.setIngredients(ingredients);

        return recipeRepository.save(recipe);
    }

    /**
     *
     * @param id recipeId
     * @return a single recipe response
     */
    public SingleRecipeResponse getById(int id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe == null) {
            return null;
        }
        List<IngredientWrapper> wrappers = new ArrayList<>();
        for (Ingredient in : recipe.getIngredients()) {
            AmazonUrl url = urlService.getByName(in.getName());
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

    /**
     *
     * @return all recipes from database
     */
    // TODO: Get all recipes from database then map to a simplified version of Recipe response
    // eg. Simplified recipe only has [ID, name, image url]
    public List<SimplifiedRecipe> getAll() {
        //return recipeRepository.getAllRecipes();
        List<Recipe> recipes = recipeRepository.getAllRecipes();
        List<SimplifiedRecipe> simplifiedRecipes = new ArrayList<>();
        for (Recipe re : recipes) {
            SimplifiedRecipe sr = SimplifiedRecipe.builder()
                    .id(re.getId())
                    .name(re.getName())
                    .imageUrl(re.getImageUrl())
                    .build();
            simplifiedRecipes.add(sr);
        }
        return simplifiedRecipes;
    }
}
