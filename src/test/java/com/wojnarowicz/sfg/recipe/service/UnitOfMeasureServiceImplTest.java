package com.wojnarowicz.sfg.recipe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wojnarowicz.sfg.recipe.command.UnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;
import com.wojnarowicz.sfg.recipe.repository.UnitOfMeasureRepository;
import com.wojnarowicz.sfg.recipe.service.impl.UnitOfMeasureServiceImpl;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
  
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void testFindByName() {
    }

    @Test
    void testFindAllAsCommand() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        
        UnitOfMeasure uom1 = UnitOfMeasure.builder().id(1L).build();
        unitOfMeasures.add(uom1);
        
        UnitOfMeasure uom2 = UnitOfMeasure.builder().id(2L).build();
        unitOfMeasures.add(uom2);
        
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
        
        Set<UnitOfMeasureCommand> commands = unitOfMeasureService.findAllAsCommand();
        
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}
