package com.wojnarowicz.sfg.recipe.command;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String name;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;
}
