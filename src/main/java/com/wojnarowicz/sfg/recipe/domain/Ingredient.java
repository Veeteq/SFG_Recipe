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

@Entity
@Table(name="ingredients")
@AttributeOverride(name = "id", column = @Column(name = "ingredient_id"))
@SequenceGenerator(name="default_seq", sequenceName="ingredient_seq", allocationSize=1)
public class Ingredient extends NamedEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "uom_id")
    private UnitOfMeasure uom;
    
    public Ingredient(String name, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        super.setName(name);
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }
}
