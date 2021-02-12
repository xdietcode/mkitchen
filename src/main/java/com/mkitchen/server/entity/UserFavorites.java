package com.mkitchen.server.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserFavorites {
    @EmbeddedId
    private UserFavoritesKey id = new UserFavoritesKey();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userName")
    @JoinColumn(name = "user_name")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    LocalDateTime likesAt;

    public UserFavoritesKey getId() {
        return id;
    }

    public void setId(UserFavoritesKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public LocalDateTime getLikesAt() {
        return likesAt;
    }

    public void setLikesAt(LocalDateTime likesAt) {
        this.likesAt = likesAt;
    }
}
