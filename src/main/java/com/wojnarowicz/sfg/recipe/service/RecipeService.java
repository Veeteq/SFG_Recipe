package com.wojnarowicz.sfg.recipe.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    
    public Set<Recipe> findAll() {
        Set<Recipe> recipes = new HashSet<>();
        
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        
        return recipes;
    }
}
