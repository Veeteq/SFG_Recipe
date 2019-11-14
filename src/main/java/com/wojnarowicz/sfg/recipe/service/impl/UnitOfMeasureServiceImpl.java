package com.wojnarowicz.sfg.recipe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;
import com.wojnarowicz.sfg.recipe.repository.UnitOfMeasureRepository;
import com.wojnarowicz.sfg.recipe.service.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    
    @Autowired
    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public UnitOfMeasure findByName(String name) {
        log.debug("UnitOfMeasureService: findByName");
        return unitOfMeasureRepository.findByName(name).get();
    }

}
