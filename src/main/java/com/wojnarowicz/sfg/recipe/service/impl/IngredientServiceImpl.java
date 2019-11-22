package com.wojnarowicz.sfg.recipe.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.command.IngredientCommand;
import com.wojnarowicz.sfg.recipe.converter.IngredientToIngredientCommand;
import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.IngredientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    
    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        
        if(!optionalRecipe.isPresent()) {
            log.error("Recipe not found");
        }
        
        Recipe recipe = optionalRecipe.get();
        
        Optional<IngredientCommand> optionalIngredient = recipe.getIngredients()
        .stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
        .findFirst();
        
        if(!optionalIngredient.isPresent()) {
            log.error("Ingredient not found");
        }
        
        return optionalIngredient.get();
    }

}
