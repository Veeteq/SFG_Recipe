package com.wojnarowicz.sfg.recipe.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe save(Recipe recipe) {
        log.debug("RecipeService: save");
        return recipeRepository.save(recipe);
    }
    
    @Override
    public void saveAll(Iterable<Recipe> recipes) {
        log.debug("RecipeService: save");
        recipeRepository.saveAll(recipes);
    }
    
    @Override
    public Set<Recipe> findAll() {
        log.debug("RecipeService: findAll");
        Set<Recipe> recipes = new HashSet<>();
        
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        
        return recipes;
    }
}
