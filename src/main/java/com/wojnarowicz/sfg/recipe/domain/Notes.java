package com.wojnarowicz.sfg.recipe.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="notes")
@AttributeOverride(name = "id", column = @Column(name = "note_id"))
@SequenceGenerator(name="default_seq", sequenceName="note_seq", allocationSize=1)
@Getter
@Setter
public class Notes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "notes")
    private Recipe recipe;
    
    @Lob
    private String notes;
}
