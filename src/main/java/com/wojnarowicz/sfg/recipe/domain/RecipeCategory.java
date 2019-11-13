package com.wojnarowicz.sfg.recipe.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="recipe_categories")
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
@SequenceGenerator(name="default_seq", sequenceName="recipe_category_seq", allocationSize=1)
public class RecipeCategory extends NamedEntity {

    private static final long serialVersionUID = 1L;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
