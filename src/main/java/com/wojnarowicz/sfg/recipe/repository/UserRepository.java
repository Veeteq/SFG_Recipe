package com.wojnarowicz.sfg.recipe.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wojnarowicz.sfg.recipe.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    
    Optional<User> findByName(String name);
    
    Page<User> findAllByOrderById(Pageable pageable);
}
