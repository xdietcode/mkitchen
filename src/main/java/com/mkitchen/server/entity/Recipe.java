package com.mkitchen.server.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
@Builder
@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    private String description;

    private int totalTime;

    private int servings;
    private int caloriesPerServing;
    private String imageUrl;
}
