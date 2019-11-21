package com.wojnarowicz.sfg.recipe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.recipe.domain.User;
import com.wojnarowicz.sfg.recipe.repository.UserRepository;
import com.wojnarowicz.sfg.recipe.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        log.debug("UserService: findById");
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        log.debug("UserService: save");
        return userRepository.save(user);
    }
}
