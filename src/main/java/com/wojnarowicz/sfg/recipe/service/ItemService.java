package com.wojnarowicz.sfg.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wojnarowicz.sfg.recipe.domain.Item;

public interface ItemService {

    Item findById(Long id);

    Item save(Item item);

    Page<Item> findAll(Pageable pageable);
}
