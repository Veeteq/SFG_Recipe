package com.wojnarowicz.sfg.recipe.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="recipes")
@AttributeOverride(name = "id", column = @Column(name = "recipe_id"))
@SequenceGenerator(name="default_seq", sequenceName="recipe_seq", allocationSize=1)
@Getter
@Setter
public class Recipe extends NamedEntity {

    private static final long serialVersionUID = 1L;

    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    
    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")    
    private Set<Ingredient> ingredients = new HashSet<>();
    
    @Lob
    private Byte[] image;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notes_id")
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category", 
               joinColumns = @JoinColumn(name = "recipe_id"), 
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<RecipeCategory> categories = new HashSet<>();
    
    
    public Recipe() {
    }

    public Recipe(String name) {
        super.setName(name);
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
    
    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public void addCategory(RecipeCategory category) {
        this.categories.add(category);
    }
}
