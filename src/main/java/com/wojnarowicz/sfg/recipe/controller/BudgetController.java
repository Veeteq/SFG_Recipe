package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BudgetController {

    @Autowired
    public BudgetController() {
    }

    @RequestMapping(path = {"/budget","/budget/"})    
    public String getBudgetPage(Model model) {
        log.debug("BudgetController: getBudgetPage");
        
        return "budget/index";
    }
    
    @RequestMapping(path = {"/users","/users/"})    
    public String listUsers(Model model) {
        log.debug("BudgetController: listUsers");
        
        return "budget/index";
    }

    @RequestMapping(path = {"/items","/items/"})    
    public String listItems(Model model) {
        log.debug("BudgetController: listUsers");
        
        return "budget/index";
    }

    @RequestMapping(path = {"/categories","/categories/"})    
    public String listCategories(Model model) {
        log.debug("BudgetController: listUsers");
        
        return "budget/index";
    }
}
