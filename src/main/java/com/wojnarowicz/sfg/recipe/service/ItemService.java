package com.wojnarowicz.sfg.recipe.service;

import java.util.List;

import com.wojnarowicz.sfg.recipe.domain.Item;

public interface ItemService {

    Item findById(Long id);

    Item save(Item item);

    List<Item> findAll();
}
