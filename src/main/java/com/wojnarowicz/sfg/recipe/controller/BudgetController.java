package com.wojnarowicz.sfg.recipe.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wojnarowicz.sfg.recipe.service.CategoryService;
import com.wojnarowicz.sfg.recipe.service.ItemService;
import com.wojnarowicz.sfg.recipe.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BudgetController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    
    @Autowired
    public BudgetController(CategoryService categoryService, ItemService itemService, UserService userService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @RequestMapping(path = {"/budget","/budget/"})    
    public String getBudgetPage(Model model) {
        log.debug("BudgetController: getBudgetPage");
                
        return "budget/index";
    }
    
    @RequestMapping(path = {"/users","/users/"})    
    public String listUsers(Model model) {
        log.debug("BudgetController: listUsers");
        
        model.addAttribute("users", userService.findAll());
        
        return "budget/users";
    }

    @RequestMapping(path = {"/items","/items/"})    
    public String listItems(Model model) {
        log.debug("BudgetController: listItems");
        
        model.addAttribute("items", itemService.findAll());
        
        return "budget/items";
    }

    @RequestMapping(path = {"/categories","/categories/"})    
    public String listCategories(Model model) {
        log.debug("BudgetController: listCategories");

        model.addAttribute("categories", categoryService.findAll());
        return "budget/categories";
    }

    @RequestMapping(path = "/expense/new", method = RequestMethod.GET)
    public String getExpenseForm(Model model) {
        
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("items", itemService.findAll());
        
        return "budget/expenseform";
    }
}
