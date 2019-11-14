package com.wojnarowicz.sfg.recipe.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

    Category category;
    
    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void testGetType() {
        CategoryType type = CategoryType.Exp;
        category.setType(type);
        assertEquals(type, category.getType());
    }

    @Test
    public void testGetItems() {
    }

    @Test
    public void testGetName() {
        String name = "test name";
        category.setName(name);
        assertEquals(name, category.getName());
    }

    @Test
    public void testGetId() {
        Long id = 4l;
        category.setId(id);
        assertEquals(id, category.getId());
    }

}
