package com.wojnarowicz.sfg.recipe.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="categories")
@AttributeOverride(name = "id", column = @Column(name = "cate_id"))
@AttributeOverride(name = "name", column = @Column(name = "cate_name_tx"))
@GenericGenerator(name = "default_seq", 
                  strategy = "com.wojnarowicz.sfg.recipe.domain.BudgetSequenceGenerator", 
                  parameters = {@Parameter(name="sequence_name", value="cate_seq")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Category extends NamedEntity implements Comparable<Category> {

    private static final long serialVersionUID = 1L;

    @Column(name = "cate_type_tx")
    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Item> items;

    @Override
    public int compareTo(Category other) {
        if(other != null) {
            return this.getId().compareTo(other.getId());
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Category [categoryType=" + categoryType + ", name=" + getName() + ", id=" + getId() + "]";
    }
    
    
}
