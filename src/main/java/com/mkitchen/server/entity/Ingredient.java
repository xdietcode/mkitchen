package com.mkitchen.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue
    private int id;

    private String name; // e.g. sugar

    private double amount; // addtional column
    private String unit; // e.g. g, tbsp
}
