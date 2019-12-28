package com.wojnarowicz.sfg.recipe.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.recipe.command.RecipeCategoryCommand;
import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;

public class RecipeCategoryCommandToRecipeCategoryTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String DESCRIPTION = "description";
    RecipeCategoryCommandToRecipeCategory conveter;

    @BeforeEach
    public void setUp() throws Exception {
        conveter = new RecipeCategoryCommandToRecipeCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new RecipeCategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCategoryCommand categoryCommand = new RecipeCategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setName(DESCRIPTION);

        //when
        RecipeCategory category = conveter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getName());
    }

}