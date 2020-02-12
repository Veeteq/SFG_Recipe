package com.wojnarowicz.sfg.recipe.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wojnarowicz.sfg.recipe.command.ExpenseCommand;
import com.wojnarowicz.sfg.recipe.service.CategoryService;
import com.wojnarowicz.sfg.recipe.service.ExpenseService;
import com.wojnarowicz.sfg.recipe.service.ItemService;
import com.wojnarowicz.sfg.recipe.service.UserService;
import com.wojnarowicz.sfg.recipe.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BudgetController {

    private final static String BUDGET_EXPENSE_FORM = "budget/expenseform";
    private final static String BUDGET_INDEX_PAGE = "budget/index";
    
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
                
        return BUDGET_INDEX_PAGE;
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
    public String getExpenseForm(@RequestParam(required = false) String date, Model model) {
        log.debug("BudgetController: getExpenseForm");
        log.debug("BudgetController: PathVariable date: " + date);
        
        LocalDate operDate;
        
        if(date == null || date.isEmpty()) {
            operDate = LocalDate.now();            
        } else {
            operDate = DateUtil.parse(date);
        }
                            
    	ExpenseCommand expenseCommand = new ExpenseCommand();
    	expenseCommand.setOperDate(operDate);
    	expenseCommand.setCount(BigDecimal.ONE);
    	expenseCommand.setPrice(BigDecimal.ZERO);
        
        log.debug("dateFormat: " + dateFormat());
        log.debug("currentDate: " + operDate.toString());
        
        model.addAttribute("dateFormat", dateFormat());        
        model.addAttribute("expense", expenseCommand);
        model.addAttribute("currentDate", operDate);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("expenses", expenseService.findByOperDate(operDate));
        model.addAttribute("dailySummary", expenseService.getDailySummaryByUser(operDate));
        
        return BUDGET_EXPENSE_FORM;
    }

    @PostMapping(path = "/expense")
    public String addOrUpdateExpense(@Valid @ModelAttribute(name = "expense") ExpenseCommand expenseCommand, BindingResult result, Model model) {
        log.debug("BudgetController: addOrUpdateExpense");

        LocalDate operDate = expenseCommand.getOperDate();
        
        System.out.println(operDate.toString());
        System.out.println(expenseCommand.getCount());
        expenseCommand.setPrice(expenseCommand.getPrice().add(BigDecimal.ONE));
    	
    	//System.out.println(expenseCommand.getItem().getCategory().getName());
    	
    	model.addAttribute("currentDate", operDate);
    	model.addAttribute("users", userService.findAll());
    	model.addAttribute("items", itemService.findAll());
    	model.addAttribute("expenses", expenseService.findByOperDate(operDate));

        if(result.hasErrors()) {
            result.getAllErrors().forEach(err -> {
                log.debug(err.toString());
            });
            return BUDGET_EXPENSE_FORM;
        }
        
    	expenseService.saveExpenseCommand(expenseCommand);
    	
        return "redirect:/expense/new";
    }

    @ModelAttribute(name = "dateFormat")
    public String dateFormat() {
        return "yyyy-MM-dd";
    }

    @GetMapping(path="/datePicker")
    public String getDatePicker(Model model) {
        
        LocalDate currentDate = LocalDate.now();         
        model.addAttribute("currentDate", currentDate);
        
    	return "budget/sample05";
    }

    @PostMapping(path="/datePicker")
    public String postDatePicker(@ModelAttribute(name="currentDate") String currentDate, Model model) {
    	log.debug("currentDate: " + currentDate);
    	
    	return "budget/sample05";
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
