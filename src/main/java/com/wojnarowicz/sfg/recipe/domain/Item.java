package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "items")
@AttributeOverride(name = "id", column = @Column(name = "item_id"))
@AttributeOverride(name = "name", column = @Column(name = "item_name_tx"))
@GenericGenerator(name = "default_seq", 
                  strategy = "com.wojnarowicz.sfg.recipe.domain.BudgetSequenceGenerator", 
                  parameters = {@Parameter(name="sequence_name", value="item_seq")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Item extends NamedEntity implements Comparable<Item> {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cate_id", nullable=false)
    private Category category;

    @Override
    public int compareTo(Item other) {
        if(other != null) {
            return this.getId().compareTo(other.getId());
        }
        return -1;
    }
}
