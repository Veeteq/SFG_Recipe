package com.wojnarowicz.sfg.recipe.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Expense extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String operDate;
	private User user;
	private Item item;
	private BigDecimal count;
	private BigDecimal price;
	private String comment;
}
