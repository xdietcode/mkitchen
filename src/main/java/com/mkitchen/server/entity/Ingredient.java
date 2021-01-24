package com.mkitchen.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String name;
    private String url;

    @OneToMany(mappedBy = "ingredient")
    private Set<RecipeIngredient> recipeIngredients = new HashSet<>();

    public Ingredient(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
