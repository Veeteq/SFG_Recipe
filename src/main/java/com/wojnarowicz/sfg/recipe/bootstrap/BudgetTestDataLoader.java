package com.wojnarowicz.sfg.recipe.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.domain.Category;
import com.wojnarowicz.sfg.recipe.domain.CategoryType;
import com.wojnarowicz.sfg.recipe.domain.Item;
import com.wojnarowicz.sfg.recipe.domain.User;
import com.wojnarowicz.sfg.recipe.service.CategoryService;
import com.wojnarowicz.sfg.recipe.service.ExpenseService;
import com.wojnarowicz.sfg.recipe.service.IncomeService;
import com.wojnarowicz.sfg.recipe.service.ItemService;
import com.wojnarowicz.sfg.recipe.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile(value = {"default", "h2mem"})
@Slf4j
public class BudgetTestDataLoader implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    @Autowired
    public BudgetTestDataLoader(CategoryService categoryService, ItemService itemService, UserService userService, IncomeService incomeService, ExpenseService expenseService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
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
        categoryService.save(category1);
        
        Category category2 = Category.builder().id(4L).name("Jedzenie").categoryType(CategoryType.Exp).build();
        categoryService.save(category2);
        
        Category category3 = Category.builder().id(16L).name("NieLiczone").categoryType(CategoryType.Both).build();
        categoryService.save(category3);
        
        log.debug("Categories loaded...");
    }

    private void loadItems(boolean toBeLoaded) {
        Item item1 = Item.builder().id(513L).name("_Transfer").category(categoryService.findById(16L)).build();
        itemService.save(item1);
        
        Item item2 = Item.builder().id(458L).name("Inne").category(categoryService.findById(3L)).build();
        itemService.save(item2);
        
        Item item3 = Item.builder().id(248L).name("Obiad").category(categoryService.findById(4L)).build();
        itemService.save(item3);        
        
        log.debug("Items loaded...");
    }

    private void loadUsers(boolean toBeLoaded) {
        User user1 = User.builder().id(5L).name("mBank").build();
        userService.save(user1);
        
        User user2 = User.builder().id(20L).name("CitiBank").build();
        userService.save(user2);
        
        User user3 = User.builder().id(22L).name("CitiMagda").build();
        userService.save(user3);
        
        User user4 = User.builder().id(30L).name("IdeaBank").build();
        userService.save(user4);
        
        log.debug("Users loaded...");
    }

    private void loadIncomes(boolean toBeLoaded) {
            log.debug("Incomes loaded...");
    }

    private void loadExpenses(boolean toBeLoaded) {
            log.debug("Incomes loaded...");
    }
}