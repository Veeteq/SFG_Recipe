package com.wojnarowicz.sfg.recipe.command;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.wojnarowicz.sfg.recipe.domain.Item;
import com.wojnarowicz.sfg.recipe.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpenseCommand {

    private Long id;
 
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate operDate;
    
    @NotNull
    private User user;
    
    @NotNull
    private Item item;
    
    @Positive
    private BigDecimal count;
    
    @Positive
    private BigDecimal price;
    
    private String comment;
}
