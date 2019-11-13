package com.wojnarowicz.sfg.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;
import com.wojnarowicz.sfg.recipe.repository.RecipeCategoryRepository;

@Service
public class RecipeCategoryService {

    private RecipeCategoryRepository recipeCategoryRepository;
    
    @Autowired
    public RecipeCategoryService(RecipeCategoryRepository recipeCategoryRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
    }

    public RecipeCategory findByName(String name) {
        return recipeCategoryRepository.findByName(name).get();
    }

}
