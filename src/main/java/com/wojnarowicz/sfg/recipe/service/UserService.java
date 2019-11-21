package com.wojnarowicz.sfg.recipe.service;

import com.wojnarowicz.sfg.recipe.domain.User;

public interface UserService {

    User findById(Long id);

    User save(User user);
}
