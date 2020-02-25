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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "incomes")
@AttributeOverride(name = "id", column = @Column(name = "inco_id"))
@GenericGenerator(name = "default_seq", 
                  strategy = "com.wojnarowicz.sfg.recipe.domain.BudgetSequenceGenerator", 
                  parameters = {@Parameter(name="sequence_name", value="inco_seq")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Income extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "oper_dt")
    private LocalDate operationDate;
    
    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item item;
    
    @Column(name = "inco_item_am")
    private BigDecimal amount;
    
    @Column(name = "inco_comm_tx")
    private String comment;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public BigDecimal getTotal() {
        return this.amount.setScale(2, RoundingMode.HALF_EVEN);
    }
}
