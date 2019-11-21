package com.wojnarowicz.sfg.recipe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wojnarowicz.sfg.recipe.converter.RecipeCommandToRecipe;
import com.wojnarowicz.sfg.recipe.converter.RecipeToRecipeCommand;
import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.impl.RecipeServiceImpl;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    
    @Mock
    RecipeRepository recipeRepository;
    
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void testFindAll() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesSet = new HashSet<>();
        recipesSet.add(recipe);
        
        when(recipeRepository.findAll()).thenReturn(recipesSet);
        
        Set<Recipe> recipes = recipeService.findAll();
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }
    
    @Test
    public void findById() {
        Long recipeId = 1L;
        String recipeName = "TestRecipe";
        
        //given
        Recipe recipe = Recipe.builder()
                .id(recipeId)
                .name(recipeName)
                .build();
        Optional<Recipe> optRecipe = Optional.of(recipe);
        
        //when
        when(recipeRepository.findById(anyLong())).thenReturn(optRecipe);
        
        //then
        Recipe retRecipe = recipeService.findById(1L);
        assertNotNull(retRecipe);
        assertEquals(recipeId, retRecipe.getId());
        assertEquals(recipeName, retRecipe.getName());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
    
    @Test
    public void testDeleteById() {
        //given
        
        Long longId = 2L;
        
        //when
        recipeRepository.deleteById(longId);
        
        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}
