package com.mkitchen.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Integer id;

    @Column(length = 20) // maximum size of name
    private String name;
    private String instruction;
    private int prepTime;
    private int servings;
    private int caloriesPerServing;
    private String imageUrl;

    @OneToMany(targetEntity = Ingredient.class, cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;

    public void addIngredient(Ingredient recipeIngredient) {
        this.ingredients.add(recipeIngredient);
    }

}
