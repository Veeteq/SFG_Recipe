package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wojnarowicz.sfg.recipe.command.IngredientCommand;
import com.wojnarowicz.sfg.recipe.command.RecipeCommand;
import com.wojnarowicz.sfg.recipe.command.UnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.service.IngredientService;
import com.wojnarowicz.sfg.recipe.service.RecipeService;
import com.wojnarowicz.sfg.recipe.service.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;
    
    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping(path = "/recipe/{id}/ingredients", method = RequestMethod.GET)
    public String getIngredients(@PathVariable String id, Model model) {
        log.debug("IngredientController: getIngredients");
        
        Long longId = Long.parseLong(id);
        
        model.addAttribute("recipe", recipeService.findCommandById(longId));
        return "recipes/ingredients";
    }
    
    @RequestMapping(path = "/recipe/{recipeId}/ingredient/{ingredientId}/show", method = RequestMethod.GET)    
    public String getRecipeIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("IngredientController: getRecipeIngredientById: " + recipeId + ", " + ingredientId);
        
        Long recipeLongId = Long.parseLong(recipeId);
        Long ingredientLongId = Long.parseLong(ingredientId);
        
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeLongId, ingredientLongId));
        
        return "recipes/ingredients/show";
    }
    
    @RequestMapping(path = "/recipe/{recipeId}/ingredient/new", method = RequestMethod.GET)    
    public String newRecipeIngredientForm(@PathVariable String recipeId, Model model) {
        log.debug("IngredientController: newRecipeIngredientForm");

        Long recipeLongId = Long.parseLong(recipeId);        
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeLongId);
        
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeCommand.getId());
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureService.findAllAsCommand());
        
        return "recipes/ingredients/ingredientform";
    }
    
    @RequestMapping(path = "/recipe/{recipeId}/ingredient/{ingredientId}/edit", method = RequestMethod.GET)    
    public String editRecipeIngredientForm(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("IngredientController: editRecipeIngredientForm: " + recipeId + ", " + ingredientId);
        
        Long recipeLongId = Long.parseLong(recipeId);
        Long ingredientLongId = Long.parseLong(ingredientId);
        
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeLongId, ingredientLongId));
        model.addAttribute("uomList", unitOfMeasureService.findAllAsCommand());
        
        return "recipes/ingredients/ingredientform";
    }

    @RequestMapping(path = "/recipe/{recipeId}/ingredient", method = RequestMethod.POST)    
    public String addOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        log.debug("IngredientController: addOrUpdateIngredient");

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        
        log.debug("Recipe ID: " + ingredientCommand.getRecipeId());
        log.debug("Ingredient ID: " + ingredientCommand.getId());
        
        //model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeLongId, ingredientLongId));
        
        return "redirect:/recipe/" + savedIngredientCommand.getRecipeId()+ "/ingredient/" + savedIngredientCommand.getId()+ "/show";
    }

    @RequestMapping(path = "/recipe/{recipeId}/ingredient/{ingredientId}/delete", method = RequestMethod.GET)
    public String deleteRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("IngredientController: deleteRecipeIngredient: " + recipeId + ", " + ingredientId);
        
        Long recipeLongId = Long.parseLong(recipeId);
        Long ingredientLongId = Long.parseLong(ingredientId);

        ingredientService.deleteByRecipeIdAndIngredientId(recipeLongId, ingredientLongId);
        
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
