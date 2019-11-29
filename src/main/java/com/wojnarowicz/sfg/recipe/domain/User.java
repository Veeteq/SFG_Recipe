package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@AttributeOverride(name = "name", column = @Column(name = "user_name_tx"))
//@SequenceGenerator(name = "default_seq", sequenceName = "user_seq", allocationSize = 1)
@GenericGenerator(name = "default_seq", 
                  strategy = "com.wojnarowicz.sfg.recipe.domain.BudgetSequenceGenerator", 
                  parameters = {@Parameter(name="sequence_name", value="user_seq") } )
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class User extends NamedEntity implements Comparable<User> {

	private static final long serialVersionUID = 1L;

    @Override
    public int compareTo(User other) {
        if(other != null) {
            return this.getId().compareTo(other.getId());
        }
        return -1;
    }
}
