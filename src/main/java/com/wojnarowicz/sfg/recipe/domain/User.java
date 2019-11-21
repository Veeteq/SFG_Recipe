package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@AttributeOverride(name = "name", column = @Column(name = "user_name_tx"))
@SequenceGenerator(name = "default_seq", sequenceName = "user_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class User extends NamedEntity {

	private static final long serialVersionUID = 1L;

}
