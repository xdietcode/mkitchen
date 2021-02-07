package com.mkitchen.server.model;

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
    private Double amount;
    private String unit;
    private String url;
}
