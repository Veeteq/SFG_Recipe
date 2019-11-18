package com.wojnarowicz.sfg.recipe.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemRepositoryIT {

    private final static String _american = "American";
    private final static String _thai = "Thai";
    
    @Autowired
    RecipeCategoryRepository recipeCategoryRepository;
    
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void findRecipeCategoryByName() {
        Optional<RecipeCategory> optional = recipeCategoryRepository.findByName(_american);
        Assertions.assertEquals(_american, optional.get().getName());
    }
    
    @Test
    public void findByName() {
        Optional<RecipeCategory> optional = recipeCategoryRepository.findByName(_thai);
        Assertions.assertEquals(_thai, optional.get().getName());
    }
}
