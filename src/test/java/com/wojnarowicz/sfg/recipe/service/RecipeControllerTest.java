package com.wojnarowicz.sfg.recipe.service;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.wojnarowicz.sfg.recipe.controller.RecipeController;
import com.wojnarowicz.sfg.recipe.domain.Recipe;

public class RecipeControllerTest {

    RecipeController recipeController;
    
    @Mock
    RecipeService recipeService;
    
    @Mock
    Model model;
    
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        recipeController = new RecipeController(recipeService);
    }

    @Test
    public void testGetIndexPage() {
        
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Set<Recipe> recipesSet = new HashSet<>();
        recipesSet.add(recipe);
        
        //when
        when(recipeService.findAll()).thenReturn(recipesSet);
        
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        
        //then
        String viewName = recipeController.getRecipes(model);
        Assertions.assertEquals("recipes", viewName);
        
        verify(recipeService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), eq(recipesSet));
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        
        Set<Recipe> recipesSet2 = argumentCaptor.getValue();
        Assertions.assertEquals(1, recipesSet2.size());
    }
    
    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipes/"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipes"));
    }
}
