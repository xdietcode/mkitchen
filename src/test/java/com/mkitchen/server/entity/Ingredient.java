package com.mkitchen.server.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Value
@Builder
@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String url;
}
