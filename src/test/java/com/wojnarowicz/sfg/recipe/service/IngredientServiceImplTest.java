package com.wojnarowicz.sfg.recipe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.wojnarowicz.sfg.recipe.command.IngredientCommand;
import com.wojnarowicz.sfg.recipe.converter.IngredientToIngredientCommand;
import com.wojnarowicz.sfg.recipe.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.domain.Ingredient;
import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.service.impl.IngredientServiceImpl;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    
    @Mock
    RecipeRepository recipeRepository;
    
    IngredientService ingredientService;
    
    @Autowired
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);    
    }

    @Test
    void testFindByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        
        Ingredient ingredient1 = Ingredient.builder().id(1L).build();
        Ingredient ingredient2 = Ingredient.builder().id(2L).build();
        Ingredient ingredient3 = Ingredient.builder().id(3L).build();
        
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);
        
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}
