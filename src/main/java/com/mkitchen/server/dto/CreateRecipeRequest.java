package com.mkitchen.server.dto;

import com.mkitchen.server.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeRequest {
    private Recipe recipe;
}
