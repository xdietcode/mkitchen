package com.mkitchen.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IngredientWrapper {

    private String name;
    private int amount;
    private String unit;
    private String url;
}
