package com.wojnarowicz.sfg.recipe.service;

import java.util.Set;

import com.wojnarowicz.sfg.recipe.command.UnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureService {

    UnitOfMeasure findByName(String name);

    Set<UnitOfMeasureCommand> findAllAsCommand();

}