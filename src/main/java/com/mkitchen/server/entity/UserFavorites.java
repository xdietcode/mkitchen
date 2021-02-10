package com.mkitchen.server.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class UserFavorites {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    LocalDateTime likesAt;
}
