package com.wojnarowicz.sfg.recipe.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wojnarowicz.sfg.recipe.command.ExpenseCommand;
import com.wojnarowicz.sfg.recipe.command.IncomeCommand;
import com.wojnarowicz.sfg.recipe.domain.Item;
import com.wojnarowicz.sfg.recipe.domain.User;
import com.wojnarowicz.sfg.recipe.service.CategoryService;
import com.wojnarowicz.sfg.recipe.service.ExpenseService;
import com.wojnarowicz.sfg.recipe.service.ItemService;
import com.wojnarowicz.sfg.recipe.service.UserService;
import com.wojnarowicz.sfg.recipe.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BudgetController {

    private static final String LAST_USED_USER_ID = "lastUsedUserId";
    private final static String BUDGET_EXPENSE_FORM = "budget/expenseform";
    private final static String BUDGET_INCOME_FORM = "budget/incomeform";
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
    public String listUsers(Model model, Pageable pageable) {
        log.debug("BudgetController: listUsers");
        
        Page<User> pages = userService.findAll(pageable);
                
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("users", pages.getContent());
        
        return "budget/users";
    }

    @RequestMapping(path = {"/items","/items/"})    
    public String listItems(Model model, @RequestParam(defaultValue="0") int page) {
        log.debug("BudgetController: listItems");
        
        Page<Item> pages = itemService.findAll(PageRequest.of(page, 50));
        
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("items", pages.getContent());
        
        return "budget/items";
    }

    @RequestMapping(path = {"/categories","/categories/"})    
    public String listCategories(Model model) {
        log.debug("BudgetController: listCategories");

        model.addAttribute("categories", categoryService.findAll());
        return "budget/categories";
    }

    @RequestMapping(path = "/expense/new", method = RequestMethod.GET)
    public String getExpenseForm(@RequestParam(required = false) String date, Model model, @ModelAttribute(LAST_USED_USER_ID) String lastUsedUserId) {
        log.debug("BudgetController: getExpenseForm");
        log.debug("BudgetController: PathVariable date: " + date);
        
        LocalDate operDate;
        
        if(date == null || date.isEmpty()) {
            operDate = LocalDate.now();            
        } else {
            operDate = DateUtil.parse(date);
        }
                            
    	ExpenseCommand expenseCommand = new ExpenseCommand();
    	expenseCommand.setOperationDate(operDate);
    	expenseCommand.setCount(BigDecimal.ONE);
    	expenseCommand.setPrice(BigDecimal.ZERO);
        
        model.addAttribute(LAST_USED_USER_ID, lastUsedUserId);
        model.addAttribute("dateFormat", dateFormat());        
        model.addAttribute("expense", expenseCommand);
        model.addAttribute("currentDate", operDate);
        model.addAttribute("users", userService.findAll(null));
        model.addAttribute("items", itemService.findAll(null));
        model.addAttribute("expenses", expenseService.findExpByOperDate(operDate));
        model.addAttribute("dailySummary", expenseService.getDailySummaryByUser(operDate).values());
        
        return BUDGET_EXPENSE_FORM;
    }

    @PostMapping(path = "/expense")
    public String addOrUpdateExpense(@Valid @ModelAttribute(name = "expense") ExpenseCommand expenseCommand, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        log.debug("BudgetController: addOrUpdateExpense");

        LocalDate operDate = expenseCommand.getOperationDate();
        
    	model.addAttribute("currentDate", operDate);
    	model.addAttribute("users", userService.findAll(null));
    	model.addAttribute("items", itemService.findAll(null));
    	model.addAttribute("expenses", expenseService.findExpByOperDate(operDate));
    	model.addAttribute("dailySummary", expenseService.getDailySummaryByUser(operDate).values());

        if(result.hasErrors()) {
            result.getAllErrors().forEach(err -> {
                log.debug(err.toString());
            });
            return BUDGET_EXPENSE_FORM;
        }
        
    	expenseService.saveExpenseCommand(expenseCommand);
    	
        redirectAttributes.addFlashAttribute(LAST_USED_USER_ID, expenseCommand.getUser().getId());
    	return "redirect:/expense/new?date=" + operDate;
    }

    @RequestMapping(path = "/income/new", method = RequestMethod.GET)
    public String getIncomeForm(@RequestParam(required = false) String date, Model model, @ModelAttribute(LAST_USED_USER_ID) String lastUsedUserId) {
        log.debug("BudgetController: getIncomeForm");
        
        LocalDate operDate;
        
        if(date == null || date.isEmpty()) {
            operDate = LocalDate.now();            
        } else {
            operDate = DateUtil.parse(date);
        }
                            
        IncomeCommand incomeCommand = new IncomeCommand();
        incomeCommand.setOperationDate(operDate);
        incomeCommand.setCount(BigDecimal.ONE);
        incomeCommand.setPrice(BigDecimal.ZERO);
        
        model.addAttribute(LAST_USED_USER_ID, lastUsedUserId);
        model.addAttribute("dateFormat", dateFormat());        
        model.addAttribute("income", incomeCommand);
        model.addAttribute("currentDate", operDate);
        model.addAttribute("users", userService.findAll(null));
        model.addAttribute("items", itemService.findAll(null));
        model.addAttribute("incomes", expenseService.findIncByOperDate(operDate));
        model.addAttribute("dailySummary", expenseService.getDailySummaryByUser(operDate).values());
        
        return BUDGET_INCOME_FORM;
    }

    @PostMapping(path = "/income")
    public String addOrUpdateIncome(@Valid @ModelAttribute(name = "income") IncomeCommand incomeCommand, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        log.debug("BudgetController: addOrUpdateIncome");

        LocalDate operDate = incomeCommand.getOperationDate();
        
        model.addAttribute("currentDate", operDate);
        model.addAttribute("users", userService.findAll(null));
        model.addAttribute("items", itemService.findAll(null));
        model.addAttribute("incomes", expenseService.findIncByOperDate(operDate));
        model.addAttribute("dailySummary", expenseService.getDailySummaryByUser(operDate).values());

        if(result.hasErrors()) {
            result.getAllErrors().forEach(err -> {
                log.debug(err.toString());
            });
            return BUDGET_INCOME_FORM;
        }
        
        expenseService.saveIncomeCommand(incomeCommand);
    
        redirectAttributes.addFlashAttribute(LAST_USED_USER_ID, incomeCommand.getUser().getId());
        return "redirect:/income/new?date=" + operDate;
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
