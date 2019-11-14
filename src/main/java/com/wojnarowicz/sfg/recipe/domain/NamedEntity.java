package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class NamedEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;
}
