package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="units_of_measure")
@AttributeOverride(name = "id", column = @Column(name = "uom_id"))
@SequenceGenerator(name="default_seq", sequenceName="uom_seq", allocationSize=1)
public class UnitOfMeasure extends NamedEntity {

    private static final long serialVersionUID = 1L;

}
