package com.wojnarowicz.sfg.recipe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wojnarowicz.sfg.recipe.domain.User;

public interface UserService {

    User findById(Long id);

    User save(User user);

    Page<User> findAll(Pageable pageable);
    
    List<User> getUsers();
}
