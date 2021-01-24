package com.mkitchen.server.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Value
@Builder
@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    private int totalTime;

    private int servings;
    private int caloriesPerServing;
    private String imageUrl;
}
