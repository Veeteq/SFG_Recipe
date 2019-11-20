package com.wojnarowicz.sfg.recipe.service;

import java.util.Set;

import com.wojnarowicz.sfg.recipe.command.RecipeCommand;
import com.wojnarowicz.sfg.recipe.domain.Recipe;

public interface RecipeService {

    Set<Recipe> findAll();

    Recipe findById(Long id);

    Recipe save(Recipe recipe);
    
    void saveAll(Iterable<Recipe> recipes);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

}