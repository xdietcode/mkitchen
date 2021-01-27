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
    private Integer id;

    @Column(length = 128) // maximum size of name
    private String name;
    private String instruction;
    private int prepTime;
    private int servings;
    private int caloriesPerServing;
    @Column(length = 512)
    private String imageUrl;

    @OneToMany(targetEntity = Ingredient.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Set<Ingredient> ingredients;

    public void addIngredient(Ingredient recipeIngredient) {
        this.ingredients.add(recipeIngredient);
    }

}
