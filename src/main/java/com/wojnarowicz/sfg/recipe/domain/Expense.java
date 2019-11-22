package com.wojnarowicz.sfg.recipe.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Expense extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate operDate;
	
	private User user;
	private Item item;
	private BigDecimal count;
	private BigDecimal price;
	private String comment;
}
