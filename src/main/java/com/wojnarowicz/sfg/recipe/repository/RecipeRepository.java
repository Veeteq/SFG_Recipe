package com.wojnarowicz.sfg.recipe.repository;

import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
}
