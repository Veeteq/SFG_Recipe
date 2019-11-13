package com.wojnarowicz.sfg.recipe.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable, Comparable<BaseEntity> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="default_seq")
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(BaseEntity other) {
        if (other instanceof BaseEntity) {
            return this.getId().compareTo(other.getId());
        }
        return -1;
    }

}
