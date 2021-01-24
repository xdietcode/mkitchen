package com.mkitchen.server.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue
    private Integer id;
    private Recipe recipe;
    private Ingredient ingredient;

    private String usageAmount; // addtional column

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, String usageAmount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.usageAmount = usageAmount;
    }
}
