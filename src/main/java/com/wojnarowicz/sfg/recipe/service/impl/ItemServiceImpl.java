package com.wojnarowicz.sfg.recipe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.Item;
import com.wojnarowicz.sfg.recipe.repository.ItemRepository;
import com.wojnarowicz.sfg.recipe.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

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

    @Override
    public List<Item> findAll() {
        log.debug("ItemService: findAll");
        List<Item> items = new ArrayList<>();

        itemRepository.findAllByOrderByName().forEach(items::add);
        
        return items;
    }
}
