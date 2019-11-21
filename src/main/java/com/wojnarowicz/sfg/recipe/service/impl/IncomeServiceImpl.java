package com.wojnarowicz.sfg.recipe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.Income;
import com.wojnarowicz.sfg.recipe.repository.IncomeRepository;
import com.wojnarowicz.sfg.recipe.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService {

    private IncomeRepository incomeRepository;
    
    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }


    @Override
    public Income save(Income income) {
        return incomeRepository.save(income);
    }

}
