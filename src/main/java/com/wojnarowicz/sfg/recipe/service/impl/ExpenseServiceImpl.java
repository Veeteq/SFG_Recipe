package com.wojnarowicz.sfg.recipe.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.command.ExpenseCommand;
import com.wojnarowicz.sfg.recipe.converter.ExpenseCommandToExpense;
import com.wojnarowicz.sfg.recipe.converter.ExpenseToExpenseCommand;
import com.wojnarowicz.sfg.recipe.domain.Expense;
import com.wojnarowicz.sfg.recipe.repository.ExpenseRepository;
import com.wojnarowicz.sfg.recipe.service.ExpenseService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ExpenseServiceImpl implements ExpenseService {
	
	private final ExpenseRepository expenseRepository;
	private final ExpenseCommandToExpense expenseCommandToExpense;
	private final ExpenseToExpenseCommand expenseToExpenseCommand;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseCommandToExpense expenseCommandToExpense, ExpenseToExpenseCommand expenseToExpenseCommand) {
        this.expenseRepository = expenseRepository;
        this.expenseCommandToExpense = expenseCommandToExpense;
        this.expenseToExpenseCommand = expenseToExpenseCommand;
    }

    @Override
    public Set<Expense> findAll() {
    	log.debug("ExpenseService: findAll");
    	Set<Expense> expenses = new HashSet<>();

    	expenseRepository.findAll().iterator().forEachRemaining(expenses::add);

    	return expenses;
    }

	@Override
	public Set<Expense> findByOperDate(LocalDate operDate) {
		log.debug("ExpenseService: findByOperDate: " + operDate.toString());
    	Set<Expense> expenses = new HashSet<>();

    	expenseRepository.findByOperDate(operDate).iterator().forEachRemaining(expenses::add);
    	
    	log.debug("Records found: " + expenses.size());

    	return expenses;
	}

    @Override
    public Expense save(Expense expense) {
        log.debug("ExpenseService: save");
        return expenseRepository.save(expense);
    }

    @Override
    public ExpenseCommand saveExpenseCommand(ExpenseCommand expenseCommand) {
        Expense expense = expenseCommandToExpense.convert(expenseCommand);

        Expense savedExpense = expenseRepository.save(expense);
        log.debug("expense saved with id: ", savedExpense.getId());
        return expenseToExpenseCommand.convert(savedExpense);
    }
}
