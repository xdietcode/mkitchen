package com.mkitchen.server.dto;

import com.mkitchen.server.entity.Category;
import com.mkitchen.server.entity.IngredientWrapper;
import com.mkitchen.server.entity.SubCategory;
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
    private String description;
    private String instruction;
    private int prepTime;
    private int cookTime;
    private int totalTime;
    private int servings;
    private int calories;
    private String imageUrl;
    List<IngredientWrapper> ingredient;

}
