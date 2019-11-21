package com.wojnarowicz.sfg.recipe.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

    Set<Category> findAllByOrderById();
    
    Optional<Category> findByName(String name);
}
