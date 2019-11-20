package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(path = {"/recipes","/recipes/"})    
    public String getRecipes(Model model) {
        log.debug("RecipeController: getRecipes");
        model.addAttribute("recipes", recipeService.findAll());
        
        return "recipes/recipes";
    }
    
    @RequestMapping(path = "/recipe/show/{id}")    
    public String getRecipeById(@PathVariable String id, Model model) {
        log.debug("RecipeController: getRecipeById: " + id);
        
        Long longId = Long.parseLong(id);
        
        model.addAttribute("recipe", recipeService.findById(longId));
        
        return "recipes/show";
    }
}
