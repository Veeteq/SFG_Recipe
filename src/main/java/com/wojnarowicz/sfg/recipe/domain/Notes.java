package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="notes")
@AttributeOverride(name = "id", column = @Column(name = "note_id"))
@SequenceGenerator(name="default_seq", sequenceName="note_seq", allocationSize=1)
public class Notes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "notes")
    private Recipe recipe;
    
    @Lob
    private String notes;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
