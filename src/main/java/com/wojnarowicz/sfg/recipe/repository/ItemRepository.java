package com.wojnarowicz.sfg.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
    
    Optional<Item> findByName(String name);
}
