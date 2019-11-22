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
    private Long recipeId;
    private String name;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
}
