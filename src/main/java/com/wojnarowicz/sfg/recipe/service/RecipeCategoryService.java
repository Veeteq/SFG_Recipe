package com.wojnarowicz.sfg.recipe.service;

import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;

public interface RecipeCategoryService {

    RecipeCategory findByName(String name);

}