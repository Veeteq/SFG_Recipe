package com.wojnarowicz.sfg.recipe.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "incomes")
@AttributeOverride(name = "id", column = @Column(name = "inco_id"))
@SequenceGenerator(name = "default_seq", sequenceName = "inco_seq", allocationSize = 1)
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
    
    @Column(name = "inco_am")
    private BigDecimal amount;
    
    @Column(name = "inco_comm_tx")
    private String comment;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
