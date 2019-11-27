package com.wojnarowicz.sfg.recipe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.ImageService;
import com.wojnarowicz.sfg.recipe.service.impl.ImageServiceImpl;

class ImageServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    private ImageService imageService;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    void testSaveImageFile() throws IOException {
        Long recipeId = 1L;
        
        MultipartFile multipartFile = new MockMultipartFile("imageFile", "testing.txt", "text/plain", "This is upload".getBytes());
        
        Recipe recipe = Recipe.builder().id(recipeId).build();
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        
        imageService.saveImageFile(recipeId, multipartFile);
        
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}
