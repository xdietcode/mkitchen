package com.mkitchen.server.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
@Builder
@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<RecipeIngredient> recipeIngredients;

    private String url;
}
