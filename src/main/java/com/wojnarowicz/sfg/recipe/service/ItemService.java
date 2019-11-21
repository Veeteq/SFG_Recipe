package com.wojnarowicz.sfg.recipe.service;

import java.util.Set;

import com.wojnarowicz.sfg.recipe.domain.Item;

public interface ItemService {

    Item findById(Long id);

    Item save(Item item);

    Set<Item> findAll();
}
