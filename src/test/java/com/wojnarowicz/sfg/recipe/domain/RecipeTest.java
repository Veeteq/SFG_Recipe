package com.wojnarowicz.sfg.recipe.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void recipeBuilder() {
        Recipe recipe = Recipe.builder()
                .name("Recipe")
                .title("Recipe title")
                .build();
        
        assertEquals("Recipe", recipe.getName());
        assertEquals("Recipe title", recipe.getTitle());
        assertNotNull(recipe.getCategories());
        assertNotNull(recipe.getIngredients());
    }
}
