package com.wojnarowicz.sfg.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{
    
    Optional<UnitOfMeasure> findByName(String name);
}
