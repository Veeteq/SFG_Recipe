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

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ExpenseRepositoryIT {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private IncomeRepository incomeRepository;
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @BeforeEach
    void setUp() throws Exception {
        BudgetTestDataLoader dataLoader = new BudgetTestDataLoader(categoryRepository, itemRepository, userRepository, incomeRepository, expenseRepository);
        dataLoader.run();
    }

    @Test
    void testFindExpensesByDate() {
        LocalDate operationDate = LocalDate.of(2019, Month.OCTOBER, 25);
        Set<Expense> expenses = expenseRepository.findByOperationDate(operationDate);
        assertEquals(5, expenses.size());
    }
    
    @Test
    void testRunNativeQuery() {
        LocalDate operationDate = LocalDate.of(2019, Month.OCTOBER, 25);
        Set<IDailySummaryByUser> expenses = expenseRepository.findDailySummaryByUser(operationDate);
        assertEquals(4, expenses.size());
        expenses.forEach(expense -> System.out.println(expense.getTransferFlag()));
    }
}
