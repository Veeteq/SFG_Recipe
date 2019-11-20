package com.wojnarowicz.sfg.recipe.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by jt on 6/17/17.
 */
public class RecipeCategoryTest {

    RecipeCategory category;

    @BeforeEach
    public void setUp(){
        category = new RecipeCategory();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }

}