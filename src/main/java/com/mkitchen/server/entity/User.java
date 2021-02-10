package com.mkitchen.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {

    @Id
    @Column(unique = true)
    private String userName;
    private String password;
    private boolean enabled;
    String role;

    @OneToMany(mappedBy = "user")
    private Set<UserFavorites> favorites;

}