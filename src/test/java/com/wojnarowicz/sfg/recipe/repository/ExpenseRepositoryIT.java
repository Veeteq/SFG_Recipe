package com.wojnarowicz.sfg.recipe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wojnarowicz.sfg.recipe.bootstrap.BudgetTestDataLoader;
import com.wojnarowicz.sfg.recipe.domain.Expense;
import com.wojnarowicz.sfg.recipe.dto.IDailySummaryByUser;
import com.wojnarowicz.sfg.recipe.service.CategoryService;
import com.wojnarowicz.sfg.recipe.service.ExpenseService;
import com.wojnarowicz.sfg.recipe.service.IncomeService;
import com.wojnarowicz.sfg.recipe.service.ItemService;
import com.wojnarowicz.sfg.recipe.service.UserService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ExpenseRepositoryIT {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private IncomeService incomeService;
    
    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @BeforeEach
    void setUp() throws Exception {
        BudgetTestDataLoader dataLoader = new BudgetTestDataLoader(categoryService, itemService, userService, incomeService, expenseService);
        dataLoader.run();
    }

    @Test
    void testFindExpensesByDate() {
        LocalDate operDate = LocalDate.of(2019, Month.OCTOBER, 25);
        Set<Expense> expenses = expenseService.findByOperDate(operDate);
        assertEquals(5, expenses.size());
    }
    
    @Test
    void testRunNativeQuery() {
        LocalDate operDate = LocalDate.of(2019, Month.OCTOBER, 25);
        Set<IDailySummaryByUser> expenses = expenseRepository.findDailySummaryByUser(operDate);
        assertEquals(4, expenses.size());
        expenses.forEach(expense -> System.out.println(expense.getTransferFlag()));
    }
}
