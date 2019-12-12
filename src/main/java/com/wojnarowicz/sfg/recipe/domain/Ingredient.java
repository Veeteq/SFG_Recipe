package com.wojnarowicz.sfg.recipe.domain;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="ingredients")
@AttributeOverride(name = "id", column = @Column(name = "ingredient_id"))
@AttributeOverride(name = "name", column = @Column(name = "ingredient_name"))
@SequenceGenerator(name="default_seq", sequenceName="ingredient_seq", allocationSize=1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Ingredient extends NamedEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "uom_id")
    private UnitOfMeasure uom;
   
    public Ingredient(String name) {
        super.setName(name);
    }
    
    public Ingredient(String name, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        super.setName(name);
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }
}
