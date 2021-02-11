package com.mkitchen.server.repository;

import com.mkitchen.server.entity.Category;
import com.mkitchen.server.entity.Recipe;
import com.mkitchen.server.entity.UserFavorites;
import com.mkitchen.server.entity.UserFavoritesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFavoritesRepository extends JpaRepository<UserFavorites, UserFavoritesKey> {
    @Query("SELECT uf.recipe FROM UserFavorites uf WHERE uf.user.userName = ?1 ORDER BY uf.likesAt DESC")
    List<Recipe> findByUserName(String username);

    @Query("SELECT uf FROM UserFavorites uf WHERE uf.user.userName = ?1 AND uf.recipe.id = ?2")
    List<UserFavorites> getIsFav(String username, Integer recipeId);

}
