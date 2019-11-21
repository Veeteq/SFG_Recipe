package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wojnarowicz.sfg.recipe.command.RecipeCommand;
import com.wojnarowicz.sfg.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;
    
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(path = {"/recipes","/recipes/"}, method = RequestMethod.GET)    
    public String getRecipes(Model model) {
        log.debug("RecipeController: getRecipes");
        model.addAttribute("recipes", recipeService.findAll());
        
        return "recipes/recipes";
    }
    
    @RequestMapping(path = "/recipe/{id}/show", method = RequestMethod.GET)    
    public String getRecipeById(@PathVariable String id, Model model) {
        log.debug("RecipeController: getRecipeById: " + id);
        
        Long longId = Long.parseLong(id);
        
        model.addAttribute("recipe", recipeService.findById(longId));
        
        return "recipes/show";
    }
    
    @RequestMapping(path = "/recipe/{id}/edit", method = RequestMethod.GET)
    public String editRecipeById(@PathVariable String id, Model model) {
        log.debug("RecipeController: editRecipeById: " + id);
        
        Long longId = Long.parseLong(id);
        
        model.addAttribute("recipe", recipeService.findCommandById(longId));
        
        return "recipes/recipeform";
    }

    @RequestMapping(path = "/recipe/new", method = RequestMethod.GET)
    public String newRecipeForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand()); 
        return "recipes/recipeform";
    }
    
    @RequestMapping(path = "/recipe/{id}/delete", method = RequestMethod.GET)
    public String deleteRecipeById(@PathVariable String id, Model model) {
        log.debug("RecipeController: deleteRecipeById: " + id);
        
        Long longId = Long.parseLong(id);
        
        recipeService.deleteById(longId);
        
        return "redirect:/recipes";
    }

    @RequestMapping(path = "/recipe", method = RequestMethod.POST)
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }
}
