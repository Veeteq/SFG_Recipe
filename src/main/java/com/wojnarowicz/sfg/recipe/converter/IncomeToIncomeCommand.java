package com.wojnarowicz.sfg.recipe.converter;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.command.IncomeCommand;
import com.wojnarowicz.sfg.recipe.domain.Income;

import lombok.Synchronized;

@Component
public class IncomeToIncomeCommand implements Converter<Income, IncomeCommand>{

    @Synchronized
    @Override
    public IncomeCommand convert(Income source) {
        if(source == null) {
            return null;
        }
        final IncomeCommand incomeCommand = new IncomeCommand();
        incomeCommand.setId(source.getId());
        incomeCommand.setOperationDate(source.getOperationDate());
        incomeCommand.setUser(source.getUser());
        incomeCommand.setItem(source.getItem());
        incomeCommand.setCount(BigDecimal.ONE);
        incomeCommand.setPrice(source.getAmount());
        incomeCommand.setComment(source.getComment());
        return incomeCommand;
    }
}