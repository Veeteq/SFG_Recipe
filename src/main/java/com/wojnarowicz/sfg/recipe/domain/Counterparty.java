package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "counterparties")
@AttributeOverride(name = "id", column = @Column(name = "cprt_id"))
@AttributeOverride(name = "name", column = @Column(name = "cprt_name_tx"))
@SequenceGenerator(name = "default_seq", sequenceName = "pcrt_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class Counterparty extends NamedEntity {
    
    private static final long serialVersionUID = 1L;

}
