package com.wojnarowicz.sfg.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;

public interface RecipeCategoryRepository extends CrudRepository<RecipeCategory, Long>{
    
    Optional<RecipeCategory> findByName(String name);
}
