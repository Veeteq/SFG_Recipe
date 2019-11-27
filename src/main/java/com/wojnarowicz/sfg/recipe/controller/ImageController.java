package com.wojnarowicz.sfg.recipe.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
 
    @GetMapping(path = "recipe/{recipeId}/recipeimage")
    public void getImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
        
        Long recipeLongId = Long.valueOf(recipeId);
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeLongId);
        
        byte[] byteObject = new byte[recipeCommand.getImage().length];
        int i = 0;
        for(Byte b : recipeCommand.getImage()) {
          byteObject[i++] = b;
        }
        
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteObject);
        IOUtils.copy(is, response.getOutputStream());
    }
}
