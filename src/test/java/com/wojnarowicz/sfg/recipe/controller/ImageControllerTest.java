package com.wojnarowicz.sfg.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.wojnarowicz.sfg.recipe.command.RecipeCommand;
import com.wojnarowicz.sfg.recipe.service.ImageService;
import com.wojnarowicz.sfg.recipe.service.RecipeService;

class ImageControllerTest {

    @Mock
    ImageService imageService;
    
    @Mock
    RecipeService recipeService;

    ImageController imageController;
    
    MockMvc mockMvc;

    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        imageController = new ImageController(imageService, recipeService);       
        mockMvc = MockMvcBuilders
                .standaloneSetup(imageController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void testUploadImageForm() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        
        //when
        mockMvc.perform(get("/recipe/1/image"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("recipe"));
        
        //then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }
    
    @Test
    void testHandleUploadImage() throws Exception {
        //given
        MockMultipartFile multipartFile = new MockMultipartFile("imageFile", "testing.txt", "text/plain", "This is upload".getBytes());
        
        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "/recipe/1/show"));
        
        verify(imageService, times(1)).saveImageFile(anyLong(), any(MultipartFile.class));
    }
    
    @Test
    void testGetImageFromDB() throws Exception {
      //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        
        String s = "Fake image text";        
        Byte[] byteObject = new Byte[s.getBytes().length];
        int i = 0;
        for (byte b : s.getBytes()){
            byteObject[i++] = b;
        }                
        recipeCommand.setImage(byteObject);
        
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
        .andExpect(status().isOk())
        .andReturn().getResponse();
        
        byte[] responseContent = response.getContentAsByteArray();
        
        assertEquals(s.getBytes().length, responseContent.length);
    }
    
    @Test
    void testGetRecipeByIdNumberFormatExc() throws Exception {
                
        mockMvc.perform(get("/recipe/number/image"))
        .andExpect(status().isBadRequest())
        .andExpect(view().name("/recipes/400error"));
    }    
}
