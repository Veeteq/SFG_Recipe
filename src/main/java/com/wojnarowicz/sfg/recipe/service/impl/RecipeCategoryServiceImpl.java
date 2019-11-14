package com.wojnarowicz.sfg.recipe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;
import com.wojnarowicz.sfg.recipe.repository.RecipeCategoryRepository;
import com.wojnarowicz.sfg.recipe.service.RecipeCategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeCategoryServiceImpl implements RecipeCategoryService {

    private RecipeCategoryRepository recipeCategoryRepository;
    
    @Autowired
    public RecipeCategoryServiceImpl(RecipeCategoryRepository recipeCategoryRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
    }

    @Override
    public RecipeCategory findByName(String name) {
        log.debug("RecipeCategoryService: findByName");
        return recipeCategoryRepository.findByName(name).get();
    }

}
