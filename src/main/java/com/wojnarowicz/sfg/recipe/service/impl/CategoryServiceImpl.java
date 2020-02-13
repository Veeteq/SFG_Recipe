package com.wojnarowicz.sfg.recipe.service.impl;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.Category;
import com.wojnarowicz.sfg.recipe.repository.CategoryRepository;
import com.wojnarowicz.sfg.recipe.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        log.debug("CategoryService: findById");
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        log.debug("CategoryService: save");
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> findAll() {
        log.debug("CategoryService: findAll");
        Set<Category> categories = new TreeSet<>();

        categoryRepository.findAllByOrderByName().iterator().forEachRemaining(categories::add);

        return categories;
    }
}
