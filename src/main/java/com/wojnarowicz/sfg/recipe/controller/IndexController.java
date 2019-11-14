package com.wojnarowicz.sfg.recipe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;
import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;
import com.wojnarowicz.sfg.recipe.repository.RecipeCategoryRepository;
import com.wojnarowicz.sfg.recipe.repository.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

    private RecipeCategoryRepository recipeCategoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    
    @Autowired
    public IndexController(RecipeCategoryRepository recipeCategoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        log.debug("IndexController: getIndexPage");
        Optional<RecipeCategory> recipeCategoryOpt = recipeCategoryRepository.findByName("American");
        Optional<UnitOfMeasure> unitOfMeasureOpt = unitOfMeasureRepository.findByName("Tablespoon");
        
        System.out.println("Category id: " + recipeCategoryOpt.get().getId());
        System.out.println("UoM id: " + unitOfMeasureOpt.get().getId());
        
        return "index";
    }
}
