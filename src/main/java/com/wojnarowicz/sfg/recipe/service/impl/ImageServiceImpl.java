package com.wojnarowicz.sfg.recipe.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.ImageService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;
    
    @Autowired
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        
        if(!optionalRecipe.isPresent()) {
            throw new RuntimeException("Recipe not found!");
        }
        
        try {
            Byte[] byteObject = new Byte[file.getBytes().length];
            
            int i = 0;
            for(Byte b : file.getBytes()) {
              byteObject[i++] = b;
            }

            Recipe recipe = optionalRecipe.get();
            recipe.setImage(byteObject);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("error occured during loading image");
            e.printStackTrace();
        }
        log.debug("Received an image"); 
    }
}
