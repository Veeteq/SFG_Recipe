package com.wojnarowicz.sfg.recipe.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.domain.Category;
import com.wojnarowicz.sfg.recipe.domain.CategoryType;
import com.wojnarowicz.sfg.recipe.domain.Expense;
import com.wojnarowicz.sfg.recipe.domain.Income;
import com.wojnarowicz.sfg.recipe.domain.Item;
import com.wojnarowicz.sfg.recipe.domain.User;
import com.wojnarowicz.sfg.recipe.repository.CategoryRepository;
import com.wojnarowicz.sfg.recipe.repository.ExpenseRepository;
import com.wojnarowicz.sfg.recipe.repository.IncomeRepository;
import com.wojnarowicz.sfg.recipe.repository.ItemRepository;
import com.wojnarowicz.sfg.recipe.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile(value = {"default", "h2mem"})
@Slf4j
public class BudgetTestDataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public BudgetTestDataLoader(CategoryRepository categoryRepository, ItemRepository itemRepository, UserRepository userRepository, IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories(true);
        loadItems(true);
        loadUsers(true);
        loadIncomes(true);
        loadExpenses(true);
    }

    private void loadCategories(boolean toBeLoaded) {
        Category category1 = Category.builder().id(3L).name("Inne").categoryType(CategoryType.Exp).build();
        categoryRepository.save(category1);
        
        Category category2 = Category.builder().id(4L).name("Jedzenie").categoryType(CategoryType.Exp).build();
        categoryRepository.save(category2);
        
        Category category3 = Category.builder().id(16L).name("NieLiczone").categoryType(CategoryType.Both).build();
        categoryRepository.save(category3);
        
        log.debug("Categories loaded..." + categoryRepository.count());
    }

    private void loadItems(boolean toBeLoaded) {    	
        Item item1 = Item.builder().id(513L).name("_Transfer").category(categoryRepository.findById(16L).orElse(null)).build();
        itemRepository.save(item1);
        
        Item item2 = Item.builder().id(458L).name("Inne").category(categoryRepository.findById(3L).orElse(null)).build();
        itemRepository.save(item2);
        
        Item item3 = Item.builder().id(248L).name("Obiad").category(categoryRepository.findById(4L).orElse(null)).build();
        itemRepository.save(item3);        
        
        Item item4 = Item.builder().id(514L).name("_Unknown").category(categoryRepository.findById(16L).orElse(null)).build();
        itemRepository.save(item4);
        
        log.debug("Items loaded..." + itemRepository.count());
    }

    private void loadUsers(boolean toBeLoaded) {
        User user1 = User.builder().id(5L).name("mBank").build();
        userRepository.save(user1);
        
        User user2 = User.builder().id(20L).name("CitiBank").build();
        userRepository.save(user2);
        
        User user3 = User.builder().id(22L).name("CitiMagda").build();
        userRepository.save(user3);
        
        User user4 = User.builder().id(30L).name("IdeaBank").build();
        userRepository.save(user4);
        
        log.debug("Users loaded..." + userRepository.count());
    }

    private void loadIncomes(boolean toBeLoaded) {
    	Income income1 = Income.builder()
    			.id(20556L)
    			.operationDate(LocalDate.of(2019, Month.OCTOBER, 25))
    			.item(itemRepository.findById(513L).orElse(null))    			
    			.amount(BigDecimal.valueOf(1344.86))
    			.comment("IdeaBank -> CitiBank")
    			.user(userRepository.findById(20L).orElse(null))
    			.build();
    	incomeRepository.save(income1);

    	Income income2 = Income.builder()
    			.id(20557L)
    			.operationDate(LocalDate.of(2019, Month.OCTOBER, 25))
    			.item(itemRepository.findById(513L).orElse(null))
    			.amount(BigDecimal.valueOf(566.28))
    			.comment("IdeaBank -> CitiMagda")
    			.user(userRepository.findById(22L).orElse(null))
    			.build();
    	incomeRepository.save(income2);
    	
    	log.debug("Incomes loaded..." + incomeRepository.count());
    }

    private void loadExpenses(boolean toBeLoaded) {
    	Expense expense1 = Expense.builder()
    			.id(72126L)
    			.operationDate(LocalDate.of(2019, Month.OCTOBER, 25))
    			.item(itemRepository.findById(248L).orElse(null))
    			.count(BigDecimal.valueOf(2))
    			.price(BigDecimal.valueOf(14.90))
    			.comment("Pierogarnia Krzycka")
    			.user(userRepository.findById(5L).orElse(null))
    			.build();
    	expenseRepository.save(expense1);
    	
    	Expense expense2 = Expense.builder()
    			.id(71882L)
    			.operationDate(LocalDate.of(2019, Month.OCTOBER, 25))
    			.item(itemRepository.findById(458L).orElse(null))
    			.count(BigDecimal.valueOf(1))
    			.price(BigDecimal.valueOf(130))
    			.comment("Witek - But ortopedyczny Walker")
    			.user(userRepository.findById(20L).orElse(null))
    			.build();
    	expenseRepository.save(expense2);
    	
    	Expense expense3 = Expense.builder()
    			.id(71868L)
    			.operationDate(LocalDate.of(2019, Month.OCTOBER, 25))
    			.item(itemRepository.findById(514L).orElse(null))
    			.count(BigDecimal.valueOf(1))
    			.price(BigDecimal.valueOf(3495))
    			.comment("Działalność - VAT")
    			.user(userRepository.findById(30L).orElse(null))
    			.build();
    	expenseRepository.save(expense3);
    	
    	Expense expense4 = Expense.builder()
    			.id(71867L)
    			.operationDate(LocalDate.of(2019, Month.OCTOBER, 25))
    			.item(itemRepository.findById(513L).orElse(null))
    			.count(BigDecimal.valueOf(1))
    			.price(BigDecimal.valueOf(566,28))
    			.comment("IdeaBank -> CitiMagda")
    			.user(userRepository.findById(30L).orElse(null))
    			.build();
    	expenseRepository.save(expense4);
    	
    	Expense expense5 = Expense.builder()
    			.id(71866L)
    			.operationDate(LocalDate.of(2019, Month.OCTOBER, 25))
    			.item(itemRepository.findById(513L).orElse(null))
    			.count(BigDecimal.valueOf(1))
    			.price(BigDecimal.valueOf(1344,86))
    			.comment("IdeaBank -> CitiBank")
    			.user(userRepository.findById(30L).orElse(null))
    			.build();
    	expenseRepository.save(expense5);
    	
        log.debug("Incomes loaded..." + expenseRepository.count());
    }
}