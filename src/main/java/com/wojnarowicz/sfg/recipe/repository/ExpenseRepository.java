package com.wojnarowicz.sfg.recipe.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long>{

	Set<Expense> findByOperDate(LocalDate operDate);

}
