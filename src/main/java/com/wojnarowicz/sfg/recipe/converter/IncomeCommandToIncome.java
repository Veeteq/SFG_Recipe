package com.wojnarowicz.sfg.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.command.IncomeCommand;
import com.wojnarowicz.sfg.recipe.domain.Income;

import lombok.Synchronized;

@Component
public class IncomeCommandToIncome implements Converter<IncomeCommand, Income>{

    @Synchronized
    @Override
    public Income convert(IncomeCommand source) {
        if(source == null) {
            return null;
        }
        final Income income = new Income();
        income.setId(source.getId());
        income.setOperationDate(source.getOperationDate());
        income.setUser(source.getUser());
        income.setItem(source.getItem());
        income.setAmount(source.getCount().multiply(source.getPrice()));
        income.setComment(source.getComment());
        return income;
    }
}
