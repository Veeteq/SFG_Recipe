package com.wojnarowicz.sfg.recipe.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngredientTest {

    private UnitOfMeasure uom;
    private Recipe recipe;
    
    @BeforeEach
    void setUp() throws Exception {
        uom = new UnitOfMeasure();
        uom.setId(1L);
        uom.setName("Piece");
        
        recipe = Recipe.builder()
                .name("Recipe")
                .build();
    }

    @Test
    void buildIngredient() {
        Ingredient ingredient = Ingredient.builder()
        .name("Avocado")
        .amount(new BigDecimal(2))
        .uom(uom)
        .recipe(recipe)
        .build();
        
        assertEquals("Avocado", ingredient.getName());
        assertEquals(2, ingredient.getAmount().longValue());
        assertNotNull(ingredient.getUom());
        assertEquals("Piece", ingredient.getUom().getName());
        assertNotNull(ingredient.getRecipe());
        assertEquals("Recipe", ingredient.getRecipe().getName());
    }

    @Test
    void ingredientContructor() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Avocado");
        ingredient.setAmount(new BigDecimal(2));
        ingredient.setUom(uom);
        ingredient.setRecipe(recipe);        
        
        assertEquals("Avocado", ingredient.getName());
        assertEquals(2, ingredient.getAmount().longValue());
        assertNotNull(ingredient.getUom());
        assertEquals("Piece", ingredient.getUom().getName());
        assertNotNull(ingredient.getRecipe());
        assertEquals("Recipe", ingredient.getRecipe().getName());
    }

}
