package com.mkitchen.server.dto;

import com.mkitchen.server.entity.IngredientWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SingleRecipeResponse {

    private String name;
    private String instruction;
    private int prepTime;
    private int servings;
    private int caloriesPerServing;
    private String imageUrl;
    List<IngredientWrapper> ingredient;

}
