package com.wojnarowicz.sfg.recipe.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.recipe.command.UnitOfMeasureCommand;
import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long LONG_ID = 2L;
    private static final String NAME = "Spoon";
    private UnitOfMeasureCommandToUnitOfMeasure converter;
    
    @BeforeEach
    void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullInput() {
        assertNull(converter.convert(null));
    }

    @Test
    void testNotNullInput() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }
    
    @Test
    void testConvert() {
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(LONG_ID);
        uomCommand.setName(NAME);
        
        UnitOfMeasure uom = converter.convert(uomCommand);
        
        assertNotNull(uom);
        assertEquals(LONG_ID, uom.getId());
        assertEquals(NAME, uom.getName());
    }
}
