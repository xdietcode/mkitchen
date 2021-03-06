package com.mkitchen.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class SimplifiedRecipe {
    private int id;
    private String name;
    private String imageUrl;

}
