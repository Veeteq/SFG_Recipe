package com.wojnarowicz.sfg.recipe.service;

import com.wojnarowicz.sfg.recipe.command.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

}
