package com.wojnarowicz.sfg.recipe.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.command.RecipeCommand;
import com.wojnarowicz.sfg.recipe.converter.RecipeCommandToRecipe;
import com.wojnarowicz.sfg.recipe.converter.RecipeToRecipeCommand;
import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> findAll() {
        log.debug("RecipeService: findAll");
        Set<Recipe> recipes = new HashSet<>();
        
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        if(!optional.isPresent()) {
            throw new RuntimeException("Recipe not found!");            
        }
        return optional.get();
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
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {        
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        if(!optional.isPresent()) {
            throw new RuntimeException("Recipe not found!");            
        }
        
        return recipeToRecipeCommand.convert(optional.get());
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
