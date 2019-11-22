package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wojnarowicz.sfg.recipe.service.IngredientService;
import com.wojnarowicz.sfg.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping(path = "/recipe/{id}/ingredients", method = RequestMethod.GET)
    public String getIngredients(@PathVariable String id, Model model) {
        log.debug("IngredientController: getIngredients");
        
        Long longId = Long.parseLong(id);
        
        model.addAttribute("recipe", recipeService.findCommandById(longId));
        return "recipes/ingredients";
    }
    
    @RequestMapping(path = "/recipe/{recipeId}/ingredient/{id}/show", method = RequestMethod.GET)    
    public String getRecipeIngredientById(@PathVariable String recipeId, @PathVariable String id, Model model) {
        log.debug("IngredientController: getRecipeIngredientById: " + recipeId + ", " + id);
        
        Long recipeLongId = Long.parseLong(recipeId);
        Long longId = Long.parseLong(id);
        
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeLongId, longId));
        
        return "recipes/ingredients/show";
    }
    
}
