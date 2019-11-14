package com.wojnarowicz.sfg.recipe.service;

import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureService {

    UnitOfMeasure findByName(String name);

}