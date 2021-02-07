package com.mkitchen.server.service;

import com.mkitchen.server.entity.Category;
import com.mkitchen.server.model.SimplifiedSubCategory;
import com.mkitchen.server.entity.SubCategory;
import com.mkitchen.server.repository.CategoryRepository;
import com.mkitchen.server.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category save(Category cat) {
        return categoryRepository.save(cat);
    }

    public List<SimplifiedSubCategory> getAllSubCategories() {
        List<SubCategory> subCategories = subCategoryRepository.getAllSubCategories();
        List<SimplifiedSubCategory> simplifiedSubCategories = new ArrayList<>();
        for (SubCategory sub : subCategories) {
            SimplifiedSubCategory simplifiedSub = SimplifiedSubCategory.builder()
                    .id(sub.getId())
                    .name(sub.getName())
                    .build();
            simplifiedSubCategories.add(simplifiedSub);
        }
        return simplifiedSubCategories;
    }
}
