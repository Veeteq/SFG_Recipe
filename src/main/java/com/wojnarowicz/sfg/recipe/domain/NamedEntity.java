package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class NamedEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

}
