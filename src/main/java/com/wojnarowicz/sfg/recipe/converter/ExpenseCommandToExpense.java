package com.wojnarowicz.sfg.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.command.ExpenseCommand;
import com.wojnarowicz.sfg.recipe.domain.Expense;

import lombok.Synchronized;

@Component
public class ExpenseCommandToExpense implements Converter<ExpenseCommand, Expense>{

    @Synchronized
    @Override
    public Expense convert(ExpenseCommand source) {
        if(source == null) {
        return null;
        }
        final Expense expense = new Expense();
        expense.setId(source.getId());
        expense.setOperationDate(source.getOperationDate());
        expense.setUser(source.getUser());
        expense.setItem(source.getItem());
        expense.setCount(source.getCount());
        expense.setPrice(source.getPrice());
        expense.setComment(source.getComment());
        return expense;
    }
}
