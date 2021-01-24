package com.mkitchen.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_ingredient_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private String usageAmount; // addtional column

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, String usageAmount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.usageAmount = usageAmount;
    }
}
