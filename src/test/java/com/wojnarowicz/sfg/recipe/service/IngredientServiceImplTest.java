package com.wojnarowicz.sfg.recipe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import com.wojnarowicz.sfg.recipe.converter.IngredientCommandToIngredient;
import com.wojnarowicz.sfg.recipe.converter.IngredientToIngredientCommand;
import com.wojnarowicz.sfg.recipe.converter.UnitOfMeasureCommandToUnitOfMeasure;
import com.wojnarowicz.sfg.recipe.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.domain.Ingredient;
import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.repository.RecipeRepository;
import com.wojnarowicz.sfg.recipe.repository.UnitOfMeasureRepository;
import com.wojnarowicz.sfg.recipe.service.impl.IngredientServiceImpl;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    
    IngredientService ingredientService;
    
    @Autowired
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);    
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
    
    @Test
    void testSaveRecipeCommand() {
        IngredientCommand command = new IngredientCommand();
        command.setId(2L);
        command.setRecipeId(3L);
        
        Optional<Recipe> optionalRecipe = Optional.of(new Recipe());
        
        Recipe recipe = new Recipe();
        recipe.setId(3L);
        recipe.addIngredient(new Ingredient());
        recipe.getIngredients().iterator().next().setId(2L);
        
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        when(recipeRepository.save(any())).thenReturn(recipe);
        
        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(command);
        
        assertEquals(Long.valueOf(2L), savedIngredientCommand.getId());
        assertEquals(Long.valueOf(3L), savedIngredientCommand.getRecipeId());
    }
    
    @Test
    void deleteByRecipeIdAndIngredientId() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        
        Recipe recipe = new Recipe();
        recipe.setId(3L);
        recipe.addIngredient(ingredient);
        
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        
        //when
        ingredientService.deleteByRecipeIdAndIngredientId(3L, 2L);

        //then
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}
