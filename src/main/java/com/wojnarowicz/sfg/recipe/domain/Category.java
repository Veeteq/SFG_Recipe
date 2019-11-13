package com.wojnarowicz.sfg.recipe.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="categories")
@AttributeOverride(name = "id", column = @Column(name = "cate_id"))
@AttributeOverride(name = "name", column = @Column(name = "cate_name_tx"))
@SequenceGenerator(name="default_seq", sequenceName="cate_seq", allocationSize=1)
public class Category extends NamedEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "cate_type_tx")
    private CategoryType type;

    @OneToMany(mappedBy = "category")
    private Set<Item> items;
    
    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
