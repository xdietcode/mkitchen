package com.mkitchen.server.controller;

import com.mkitchen.server.entity.*;
import com.mkitchen.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CategoryController {
    @Autowired
    private CategoryService catService;

    @PostMapping("/postCat")
    private Category addCategory(@RequestBody Category cat) {
        System.out.println("Category Received: " + cat);
        return catService.save(cat);
    }

    @GetMapping("/getCatByName/{cat}")
    private Category getCatByName(@PathVariable String cat) {
        return catService.getByName(cat);
    }

    /****************************************************************************
    * This API can only return a list of SimplifiedSubCategory, it will have 500
    * internal error if return a list of SubCategory. SubCategory is huge because
    * there is a many to many relationship to Recipe.
    ****************************************************************************/
    @GetMapping("/getAllSubCats")
    private List<SimplifiedSubCategory> getAllSubCats() {
        return catService.getAllSubCategories();
    }
}
