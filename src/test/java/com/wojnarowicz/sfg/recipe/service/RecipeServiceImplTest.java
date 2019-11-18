package com.wojnarowicz.sfg.recipe.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.impl.RecipeServiceImpl;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    
    @Mock
    RecipeRepository recipeRepository;
    
    @BeforeEach
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
        Assertions.assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}
