package com.wojnarowicz.sfg.recipe.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="categories")
@AttributeOverride(name = "id", column = @Column(name = "cate_id"))
@AttributeOverride(name = "name", column = @Column(name = "cate_name_tx"))
@SequenceGenerator(name="default_seq", sequenceName="cate_seq", allocationSize=1)
@Getter
@Setter
public class Category extends NamedEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "cate_type_tx")
    private CategoryType type;

    @OneToMany(mappedBy = "category")
    private Set<Item> items;
}
