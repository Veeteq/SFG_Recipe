package com.wojnarowicz.sfg.recipe.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.recipe.command.RecipeCategoryCommand;
import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;

/**
 * Created by jt on 6/21/17.
 */
public class RecipeCategoryToRecipeCategoryCommandTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String DESCRIPTION = "descript";
    RecipeCategoryToRecipeCategoryCommand convter;

    @BeforeEach
    public void setUp() throws Exception {
        convter = new RecipeCategoryToRecipeCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new RecipeCategory()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCategory category = new RecipeCategory();
        category.setId(ID_VALUE);
        category.setName(DESCRIPTION);

        //when
        RecipeCategoryCommand categoryCommand = convter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getName());

    }

}