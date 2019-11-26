package com.wojnarowicz.sfg.recipe.service.impl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.command.UnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;
import com.wojnarowicz.sfg.recipe.repository.UnitOfMeasureRepository;
import com.wojnarowicz.sfg.recipe.service.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    @Autowired
    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public UnitOfMeasure findByName(String name) {
        log.debug("UnitOfMeasureService: findByName");
        return unitOfMeasureRepository.findByName(name).get();
    }

    @Override
    public Set<UnitOfMeasureCommand> findAllAsCommand() {
        log.debug("UnitOfMeasureService: findAllAsCommand");
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }

}
