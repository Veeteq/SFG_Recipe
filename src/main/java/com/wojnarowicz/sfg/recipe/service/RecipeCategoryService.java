package com.wojnarowicz.sfg.recipe.service;

import java.util.Set;

import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;

public interface RecipeCategoryService {

    Set<RecipeCategory> findAll();
    RecipeCategory findByName(String name);

}