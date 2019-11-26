package com.wojnarowicz.sfg.recipe.service.impl;

import java.text.MessageFormat;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.command.IngredientCommand;
import com.wojnarowicz.sfg.recipe.converter.IngredientCommandToIngredient;
import com.wojnarowicz.sfg.recipe.converter.IngredientToIngredientCommand;
import com.wojnarowicz.sfg.recipe.domain.Ingredient;
import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.repository.UnitOfMeasureRepository;
import com.wojnarowicz.sfg.recipe.service.IngredientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    
    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(ingredientCommand.getRecipeId());
        
        if(!optionalRecipe.isPresent()) {
            log.error("Recipe not found for recipeId: " + ingredientCommand.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = optionalRecipe.get();
            
            Optional<Ingredient> optionalIngredient = recipe.getIngredients()
            .stream()
            .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
            .findFirst();
            
            if(optionalIngredient.isPresent()) {
                Ingredient ingredientFound = optionalIngredient.get();
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setName(ingredientCommand.getName());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(ingredientCommand.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UnitOfMEasure not Found!")));
            } else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }
            
            Recipe savedRecipe = recipeRepository.save(recipe);
            
            Optional<Ingredient> savedOptionalIngredient = savedRecipe.getIngredients()
            .stream()
            .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
            .findFirst();
            
            if(!savedOptionalIngredient.isPresent()) {
                savedOptionalIngredient = savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(ingredient -> ingredient.getName().equals(ingredientCommand.getName()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(ingredientCommand.getUom().getId()))
                        .findFirst();
            }
            
            return ingredientToIngredientCommand.convert(savedOptionalIngredient.get());
        }
    }

    @Override
    public void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        
        if(!optionalRecipe.isPresent()) {
            log.error(MessageFormat.format("Recipe {0} not found", recipeId));
            return;
        }
        
        Recipe recipe = optionalRecipe.get();
        
        Optional<Ingredient> optionalIngredient = recipe.getIngredients()
        .stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .findFirst();
        
        if(optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            ingredient.setRecipe(null);
            recipe.getIngredients().remove(ingredient);
            recipeRepository.save(recipe);
        } else {
            log.error(MessageFormat.format("Ingredient {0} not found", ingredientId));
        }        
    }
}
