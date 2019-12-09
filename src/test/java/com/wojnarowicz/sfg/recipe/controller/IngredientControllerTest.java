package com.wojnarowicz.sfg.recipe.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wojnarowicz.sfg.recipe.command.IngredientCommand;
import com.wojnarowicz.sfg.recipe.command.RecipeCommand;
import com.wojnarowicz.sfg.recipe.service.IngredientService;
import com.wojnarowicz.sfg.recipe.service.RecipeService;
import com.wojnarowicz.sfg.recipe.service.UnitOfMeasureService;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;
    
    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController ingredientController;
    
    MockMvc mockMvc;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void testListIngredients() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        
        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        
        //then
        mockMvc.perform(get("/recipe/1/ingredients"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipes/ingredients"))
        .andExpect(model().attributeExists("recipe"));
        
        verify(recipeService, times(1)).findCommandById(anyLong());
    }
    
    @Test
    void testShowIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipes/ingredients/view"))
        .andExpect(model().attributeExists("ingredient"));
    }
    
    @Test
    void testNewRecipeIngredientForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);
        
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.findAllAsCommand()).thenReturn(new HashSet<>());
        
        mockMvc.perform(get("/recipe/3/ingredient/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipes/ingredients/edit"))
        .andExpect(model().attributeExists("ingredient"))
        .andExpect(model().attributeExists("uomList"));
        
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void testEditRecipeIngredientForm() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(2L);
        command.setRecipeId(3L);
        
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
        when(unitOfMeasureService.findAllAsCommand()).thenReturn(new HashSet<>());
        
        mockMvc.perform(get("/recipe/3/ingredient/2/edit"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipes/ingredients/edit"))
        .andExpect(model().attributeExists("ingredient"))
        .andExpect(model().attributeExists("uomList"));
    }
    
    @Test
    void testAddOrUpdateIngredient() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(2L);
        command.setRecipeId(3L);
        
        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);
        
        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "Ingredient name"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/3/ingredients"));
    }

    @Test
    void testDeleteRecipeIngredient() throws Exception {
        mockMvc.perform(get("/recipe/2/ingredient/3/delete"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/2/ingredients"));
        
        verify(ingredientService, times(1)).deleteByRecipeIdAndIngredientId(anyLong(), anyLong());
    }
}
