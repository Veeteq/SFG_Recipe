package com.wojnarowicz.sfg.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;
import com.wojnarowicz.sfg.recipe.repository.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    
    @Autowired
    public UnitOfMeasureService(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public UnitOfMeasure findByName(String name) {
        return unitOfMeasureRepository.findByName(name).get();
    }

}
