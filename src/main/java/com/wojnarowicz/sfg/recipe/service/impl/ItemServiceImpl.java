package com.wojnarowicz.sfg.recipe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.Item;
import com.wojnarowicz.sfg.recipe.repository.ItemRepository;
import com.wojnarowicz.sfg.recipe.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item findById(Long id) {
        log.debug("ItemService: findById");
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item save(Item item) {
        log.debug("ItemService: save");
        return itemRepository.save(item);
    }
}
