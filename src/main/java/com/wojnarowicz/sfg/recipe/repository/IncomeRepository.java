package com.wojnarowicz.sfg.recipe.repository;

import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.Income;

public interface IncomeRepository extends CrudRepository<Income, Long>{

}
