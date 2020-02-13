package com.wojnarowicz.sfg.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.command.ExpenseCommand;
import com.wojnarowicz.sfg.recipe.domain.Expense;

import lombok.Synchronized;

@Component
public class ExpenseToExpenseCommand implements Converter<Expense, ExpenseCommand>{

    @Synchronized
    @Override
    public ExpenseCommand convert(Expense source) {
        if(source == null) {
        return null;
        }
        final ExpenseCommand expenseCommand = new ExpenseCommand();
        expenseCommand.setId(source.getId());
        expenseCommand.setOperationDate(source.getOperationDate());
        expenseCommand.setUser(source.getUser());
        expenseCommand.setItem(source.getItem());
        expenseCommand.setCount(source.getCount());
        expenseCommand.setPrice(source.getPrice());
        expenseCommand.setComment(source.getComment());
        return expenseCommand;
    }
}
