package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.wojnarowicz.sfg.recipe.command.RecipeCommand;
import com.wojnarowicz.sfg.recipe.exception.NotFoundException;
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

    @GetMapping(path = {"/recipes","/recipes/"})    
    public String getRecipes(Model model) {
        log.debug("RecipeController: getRecipes");
        model.addAttribute("recipes", recipeService.findAll());
        
        return "recipes/recipes";
    }
    
    @GetMapping(path = "/recipe/{id}/show")    
    public String getRecipeById(@PathVariable String id, Model model) {
        log.debug("RecipeController: getRecipeById: " + id);
        
        Long longId = Long.parseLong(id);
        
        model.addAttribute("recipe", recipeService.findById(longId));
        
        return "recipes/show";
    }
    
    @GetMapping(path = "/recipe/{id}/edit")
    public String editRecipeById(@PathVariable String id, Model model) {
        log.debug("RecipeController: editRecipeById: " + id);
        
        Long longId = Long.parseLong(id);
        
        model.addAttribute("recipe", recipeService.findCommandById(longId));
        
        return "recipes/recipeform";
    }

    @GetMapping(path = "/recipe/new")
    public String newRecipeForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand()); 
        return "recipes/recipeform";
    }
    
    @GetMapping(path = "/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id, Model model) {
        log.debug("RecipeController: deleteRecipeById: " + id);
        
        Long longId = Long.parseLong(id);
        
        recipeService.deleteById(longId);
        
        return "redirect:/recipes";
    }

    @PostMapping(path = "/recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }
    
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception exception) {
        log.debug("RecipeController: handleNotFoundException");
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("/recipes/404error");
        
        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception) {
        log.debug("RecipeController: handleNumberFormatException");
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("/recipes/400error");
        
        return modelAndView;
    }
}
