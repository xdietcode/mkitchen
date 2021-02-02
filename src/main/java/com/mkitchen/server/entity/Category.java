package com.mkitchen.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(targetEntity = SubCategory.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Set<SubCategory> subCategories;

    public void addSubCategory(SubCategory subCat) {
        this.subCategories.add(subCat);
    }
}
