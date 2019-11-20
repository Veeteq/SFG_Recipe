package com.wojnarowicz.sfg.recipe.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.recipe.command.UnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long LONG_ID = 2L;
    private static final String NAME = "Spoon";
    private UnitOfMeasureToUnitOfMeasureCommand converter;
    
    @BeforeEach
    void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullInput() {
        assertNull(converter.convert(null));
    }

    @Test
    void testNotNullInput() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }
    
    @Test
    void testConvert() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_ID);
        uom.setName(NAME);
        
        UnitOfMeasureCommand uomCommand = converter.convert(uom);
        
        assertNotNull(uomCommand);
        assertEquals(LONG_ID, uomCommand.getId());
        assertEquals(NAME, uomCommand.getName());
    }
}
