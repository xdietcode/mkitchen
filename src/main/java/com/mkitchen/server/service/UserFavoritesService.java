package com.mkitchen.server.service;


import com.mkitchen.server.dto.UserFavoriteRequest;
import com.mkitchen.server.entity.Recipe;
import com.mkitchen.server.entity.User;
import com.mkitchen.server.entity.UserFavorites;
import com.mkitchen.server.entity.UserFavoritesKey;
import com.mkitchen.server.model.SimplifiedRecipe;
import com.mkitchen.server.repository.RecipeRepository;
import com.mkitchen.server.repository.UserFavoritesRepository;
import com.mkitchen.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserFavoritesService {
    @Autowired
    private UserFavoritesRepository userFavRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public UserFavoriteRequest saveToFav(UserFavoriteRequest fav) {

        UserFavorites userFav = transferToUserFavorites(fav);
        userFavRepository.save(userFav);
        return UserFavoriteRequest.builder()
                .username(fav.getUsername())
                .recipeId(fav.getRecipeId())
                .build();
    }

    public UserFavoriteRequest deleteFromFav(UserFavoriteRequest fav) {
        UserFavorites userFav = transferToUserFavorites(fav);
        if (userFav == null) {
            return null;
        }
        userFavRepository.delete(userFav);
        return fav;
    }

    public List<SimplifiedRecipe> getFavRecipes (String username) {
        List<Recipe> recipes = userFavRepository.findByUserName(username);
        return RecipeService.simplifyRecipe(recipes);
    }

    public Boolean getIsFav (String username, Integer RecipeId) {
        List<UserFavorites> recipes = userFavRepository.getIsFav(username, RecipeId);
        if (recipes == null || recipes.size() == 0) {
            return false;
        }
        return true;
    }

    private UserFavorites transferToUserFavorites (UserFavoriteRequest fav) {
        User user = userRepository.findByName(fav.getUsername()).orElse(null);
        Recipe recipe = recipeRepository.findById(fav.getRecipeId()).orElse(null);
        UserFavoritesKey key = new UserFavoritesKey(fav.getUsername(),fav.getRecipeId());
        UserFavorites userFav = UserFavorites.builder()
                .id(key)
                .user(user)
                .recipe(recipe)
                .likesAt(LocalDateTime.now())
                .build();
        return userFav;
    }
}
