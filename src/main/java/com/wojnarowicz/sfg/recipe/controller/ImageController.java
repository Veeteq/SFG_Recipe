package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wojnarowicz.sfg.recipe.command.RecipeCommand;
import com.wojnarowicz.sfg.recipe.service.ImageService;
import com.wojnarowicz.sfg.recipe.service.RecipeService;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    @Autowired
    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }
    
    @GetMapping(path = "recipe/{recipeId}/image")
    public String uploadImageForm(@PathVariable String recipeId, Model model) {

        Long recipeLongId = Long.parseLong(recipeId);
        
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeLongId);
        
        model.addAttribute("recipe", recipeCommand);
        
        return "/recipes/uploadimageform";
    }
    
    @PostMapping(path = "recipe/{recipeId}/image")
    public String handleUploadImage(@PathVariable String recipeId, @RequestParam(name = "imageFile") MultipartFile file) {
        
        Long recipeLongId = Long.valueOf(recipeId);
        
        imageService.saveImageFile(recipeLongId, file);
        return "redirect:/recipe/" + recipeId + "/show";
    }
    
}
