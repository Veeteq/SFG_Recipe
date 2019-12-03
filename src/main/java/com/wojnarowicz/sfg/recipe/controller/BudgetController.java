package com.wojnarowicz.sfg.recipe.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        
        LocalDate operDate = LocalDate.now();            
    	//Expense expense = new Expense();
    	Expense expense = Expense.builder()
        .operDate(operDate)
        .count(BigDecimal.ZERO)
        .price(BigDecimal.ONE)
        .build();
        
        log.debug("dateFormat: " + dateFormat());
        log.debug("currentDate: " + operDate.toString());
        
        model.addAttribute("dateFormat", dateFormat());        
        model.addAttribute("expense", expense);
        model.addAttribute("currentDate", operDate);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("expenses", expenseService.findByOperDate(operDate));
        
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
        
    	expenseService.save(expense);
    	
        return "redirect:/expense/new";
    }

    @ModelAttribute
    public String dateFormat() {
        return "yyyy-MM-dd";
    }

    @GetMapping(path="/datePicker")
    public String getDatePicker(Model model) {
    	return "budget/datepicker";
    }

    @PostMapping(path="/datePicker")
    public String postDatePicker(@ModelAttribute(name="datePicker") String datePicker, Model model) {
    	log.debug("datePicker: " + datePicker);
    	
    	return "budget/datepicker";
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
