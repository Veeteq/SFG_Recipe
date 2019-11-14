package com.wojnarowicz.sfg.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
    Optional<User> findByName(String name);
}
