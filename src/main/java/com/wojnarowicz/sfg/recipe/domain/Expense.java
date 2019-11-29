package com.wojnarowicz.sfg.recipe.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "expenses")
@AttributeOverride(name = "id", column = @Column(name = "expe_id"))
@GenericGenerator(name = "default_seq", 
                  strategy = "com.wojnarowicz.sfg.recipe.domain.BudgetSequenceGenerator", 
                  parameters = {@Parameter(name="sequence_name", value="expe_seq")})

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Expense extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "expe_oper_dt")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate operDate;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne
    @JoinColumn(name="item_id", nullable=false)
	private Item item;
	
	@Column(name = "expe_item_cn")
	private BigDecimal count;
	
	@Column(name = "expe_pric_am")
	private BigDecimal price;
	
	@Column(name = "expe_comm_tx")
	private String comment;
	
	public BigDecimal getTotal() {
	    return this.count.multiply(this.price).setScale(2, RoundingMode.HALF_EVEN);
	}
}
