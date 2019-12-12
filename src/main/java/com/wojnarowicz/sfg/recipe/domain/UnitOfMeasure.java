package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="units_of_measure")
@AttributeOverride(name = "id", column = @Column(name = "uom_id"))
@AttributeOverride(name = "name", column = @Column(name = "uom_name"))
@SequenceGenerator(name="default_seq", sequenceName="uom_seq", allocationSize=1)
@NoArgsConstructor
@SuperBuilder
public class UnitOfMeasure extends NamedEntity {

    private static final long serialVersionUID = 1L;

}
