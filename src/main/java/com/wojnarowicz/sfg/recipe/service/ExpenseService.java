package com.wojnarowicz.sfg.recipe.service;

import java.time.LocalDate;
import java.util.Set;

import com.wojnarowicz.sfg.recipe.command.ExpenseCommand;
import com.wojnarowicz.sfg.recipe.domain.Expense;

public interface ExpenseService {

	Set<Expense> findAll();

	Set<Expense> findByOperDate(LocalDate operDate);

    Expense save(Expense expense);

    ExpenseCommand saveExpenseCommand(ExpenseCommand expense);

}
