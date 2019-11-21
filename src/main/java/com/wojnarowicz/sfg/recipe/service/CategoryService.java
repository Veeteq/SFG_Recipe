package com.wojnarowicz.sfg.recipe.service;

import java.util.Set;

import com.wojnarowicz.sfg.recipe.domain.Category;

public interface CategoryService {

    Category findById(Long id);

    Category save(Category category);

    Set<Category> findAll();

}
