package com.wojnarowicz.sfg.recipe.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    Category category;
    
    @BeforeEach
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void testGetType() {
        CategoryType type = CategoryType.Exp;
        category.setCategoryType(type);
        Assertions.assertEquals(type, category.getCategoryType());
    }

    @Test
    public void testGetItems() {
    }

    @Test
    public void testGetName() {
        String name = "test name";
        category.setName(name);
        Assertions.assertEquals(name, category.getName());
    }

    @Test
    public void testGetId() {
        Long id = 4l;
        category.setId(id);
        Assertions.assertEquals(id, category.getId());
    }

}
