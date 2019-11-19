package com.wojnarowicz.sfg.recipe.service;

import java.util.Set;

import com.wojnarowicz.sfg.recipe.domain.Recipe;

public interface RecipeService {

    Recipe save(Recipe recipe);

    Set<Recipe> findAll();
    void saveAll(Iterable<Recipe> recipes);
}