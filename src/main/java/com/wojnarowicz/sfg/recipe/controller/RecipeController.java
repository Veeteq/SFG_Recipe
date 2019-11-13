package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wojnarowicz.sfg.recipe.service.RecipeService;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(path = "/recipes")
    public String getRecipes(Model model) {
        
        model.addAttribute("recipes", recipeService.findAll());
        
        return "recipes";
    }
}
