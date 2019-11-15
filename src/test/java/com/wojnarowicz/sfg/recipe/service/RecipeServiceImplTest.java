package com.wojnarowicz.sfg.recipe.service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.impl.RecipeServiceImpl;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    
    @Mock
    RecipeRepository recipeRepository;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        recipeService = new RecipeServiceImpl(recipeRepository);
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
    }
}
