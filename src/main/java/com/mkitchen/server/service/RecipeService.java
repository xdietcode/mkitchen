package com.mkitchen.server.service;

import com.mkitchen.server.dto.SingleRecipeResponse;
import com.mkitchen.server.entity.*;
import com.mkitchen.server.repository.CategoryRepository;
import com.mkitchen.server.repository.IngredientRepository;
import com.mkitchen.server.repository.RecipeRepository;
import com.mkitchen.server.repository.SubCategoryRepository;
import model.IngredientWrapper;
import model.SimplifiedRecipe;
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

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
                .calories(recipe.getCalories())
                .imageUrl(recipe.getImageUrl())
                .instruction(recipe.getInstruction())
                .description(recipe.getDescription())
                .prepTime(recipe.getPrepTime())
                .cookTime(recipe.getCookTime())
                .totalTime(recipe.getPrepTime() + recipe.getCookTime())
                .servings(recipe.getServings())
                .ingredient(wrappers)
                .build();

        return res;
    }


    /**
     *
     * @return all recipes from database
     */
    public List<SimplifiedRecipe> getAll() {
        List<Recipe> recipes = recipeRepository.getAllRecipes();
        return simplifyRecipe(recipes);
    }

    /**
     *
     * @param  recipeName
     * @return a list of simplified recipes that contain the keyword name
     */
    public List<SimplifiedRecipe> getByName(String recipeName){
        List<Recipe> recipesByName = recipeRepository.findByName(recipeName);
        return simplifyRecipe(recipesByName);
    }

    /**
     *Method-getSimplifiedRecipes:
     * extract the reused code both in "getAll" and "getByName"
     */

    public List<SimplifiedRecipe> getByCat(String cat, String subCat) {
        Category category = categoryRepository.findByName(cat);
        SubCategory subCategory = subCategoryRepository.findByName(subCat,category.getId());
        List<Recipe> recipes = recipeRepository.getRecipesByCat(subCategory.getId());
        return simplifyRecipe(recipes);
    }

    // extract a list of SimplifiedRecipe from a list of Recipe
    private List<SimplifiedRecipe> simplifyRecipe(List<Recipe> recipes) {
        List<SimplifiedRecipe> simplifiedRecipes = new ArrayList<>();
        for (Recipe r : recipes) {
            SimplifiedRecipe simplified = SimplifiedRecipe.builder()
                    .id(r.getId())
                    .name(r.getName())
                    .imageUrl(r.getImageUrl())
                    .build();
            simplifiedRecipes.add(simplified);
        }
        return simplifiedRecipes;
    }
}
