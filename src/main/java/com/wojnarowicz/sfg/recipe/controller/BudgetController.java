package com.wojnarowicz.sfg.recipe.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wojnarowicz.sfg.recipe.domain.Expense;
import com.wojnarowicz.sfg.recipe.service.CategoryService;
import com.wojnarowicz.sfg.recipe.service.ExpenseService;
import com.wojnarowicz.sfg.recipe.service.ItemService;
import com.wojnarowicz.sfg.recipe.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BudgetController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    private final ExpenseService expenseService;
    
    @Autowired
    public BudgetController(CategoryService categoryService, ItemService itemService, UserService userService, ExpenseService expenseService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
        this.expenseService = expenseService;
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
        log.debug("BudgetController: getExpenseForm");
        
        LocalDate currentDate = LocalDate.now();            
    	Expense expense = new Expense();    	
        expense.setOperDate(currentDate);
        expense.setCount(BigDecimal.ZERO);
        expense.setPrice(BigDecimal.ONE);
        
        model.addAttribute("dateFormat", dateFormat());
        System.out.println("dateFormat: " + dateFormat());
        
        model.addAttribute("expense", expense);
        
        model.addAttribute("currentDate", currentDate);
        System.out.println("currentDate: " + currentDate.toString());
        
        model.addAttribute("users", userService.findAll());
        model.addAttribute("items", itemService.findAll());
        
        return "budget/expenseform";
    }
    
    @RequestMapping(params = "!submitBtn", path = "/expense", method = RequestMethod.POST)
    public String onDateChangeForm(@ModelAttribute(name = "expense") Expense expense, Model model) {
        log.debug("BudgetController: dateChangeForm");
        
        LocalDate operDate = expense.getOperDate();
        
        model.addAttribute("dateFormat", dateFormat());
        model.addAttribute("currentDate", operDate);
        model.addAttribute("expense", expense);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("expenses", expenseService.findByOperDate(operDate));
        
        return "budget/expenseform";
    }

    @RequestMapping(params = "submitBtn", path = "/expense", method = RequestMethod.POST)
    public String addOrUpdateExpense(@ModelAttribute(name = "expense") Expense expense, Model model) {
        log.debug("BudgetController: addOrUpdateExpense");
        
        LocalDate operDate = expense.getOperDate();
        
        System.out.println(operDate.toString());
        System.out.println(expense.getCount());
    	expense.setPrice(expense.getPrice().add(BigDecimal.ONE));
    	
    	System.out.println(expense.getItem().getCategory().getName());
    	
    	model.addAttribute("dateFormat", dateFormat());
    	model.addAttribute("currentDate", operDate);
    	model.addAttribute("expense", expense);
    	model.addAttribute("users", userService.findAll());
    	model.addAttribute("items", itemService.findAll());
    	model.addAttribute("expenses", expenseService.findByOperDate(operDate));
        
        return "budget/expenseform";
    }

    @ModelAttribute
    public String dateFormat() {
        return "yyyy-MM-dd";
    }
    
    /*
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat());
        CustomDateEditor dateEditor = new CustomDateEditor(sdf, true);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
    }
*/    
}
