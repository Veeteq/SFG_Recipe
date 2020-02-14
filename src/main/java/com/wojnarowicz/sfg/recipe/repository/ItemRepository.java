package com.wojnarowicz.sfg.recipe.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wojnarowicz.sfg.recipe.domain.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long>{

    Optional<Item> findByName(String name);

    Page<Item> findAllByOrderById(Pageable pageable);
    
    Page<Item> findAllByOrderByName(Pageable pageable);
}
