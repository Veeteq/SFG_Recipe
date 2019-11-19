package com.wojnarowicz.sfg.recipe.bootstrap;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.domain.Category;
import com.wojnarowicz.sfg.recipe.domain.CategoryType;
import com.wojnarowicz.sfg.recipe.domain.Income;
import com.wojnarowicz.sfg.recipe.domain.Item;
import com.wojnarowicz.sfg.recipe.domain.User;
import com.wojnarowicz.sfg.recipe.service.CategoryService;
import com.wojnarowicz.sfg.recipe.service.IncomeService;
import com.wojnarowicz.sfg.recipe.service.ItemService;
import com.wojnarowicz.sfg.recipe.service.UserService;
import com.wojnarowicz.sfg.recipe.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BudgetDataLoader implements CommandLineRunner {

    private final static Path baseDirectory = Paths.get("C:\\Users\\la289dm\\Downloads");
    //private final static Path baseDirectory = Paths.get("F:");
    
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    private final IncomeService incomeService;
    
    @Autowired
    public BudgetDataLoader(CategoryService categoryService, ItemService itemService, UserService userService, IncomeService incomeService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
        this.incomeService = incomeService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(Files.exists(baseDirectory)) {
            loadCategories();
            loadItems();
            loadUsers();
            loadIncomes(false);
        }
    }

    private void loadCategories() {
        Path path = baseDirectory.resolve("categories.txt");
    	//String file = "F:\\categories.txt";
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            Category category = Category.builder()
            .id(Long.valueOf(values[0]))
            .name(values[1])        
            .categoryType(CategoryType.valueOf(values[2]))
            .build();
            categoryService.save(category);
        });      
        log.debug("Categories loaded...");
    }

    private void loadItems() {
        Path path = baseDirectory.resolve("items.txt");
        //String file = "F:\\categories.txt";
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            Item item = Item.builder()
            .id(Long.valueOf(values[0]))
            .category(categoryService.findById(Long.parseLong(values[1])))
            .name(values[2])
            .build();
            itemService.save(item);
        });      
        log.debug("Items loaded...");
    }

    private void loadUsers() {
        Path path = baseDirectory.resolve("users.txt");
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            User user = User.builder()
            .id(Long.valueOf(values[0]))
            .name(values[1])
            .build();
            userService.save(user);
        });      
        log.debug("Users loaded...");
    }
    
    private void loadIncomes(boolean toBeLoaded) {
        if(toBeLoaded) {
            Path path = baseDirectory.resolve("incomes.txt");
            getStreamFromFile(path).forEach(line -> {
                String[] values = line.split("\t");
                String comment = values[4].equals("null") ? null : values[4];
                Income income = Income.builder()
                        .operationDate(DateUtil.parse(values[1]))
                        .item(itemService.findById(Long.parseLong(values[2])))
                        .amount(new BigDecimal(values[3]))
                        .comment(comment)
                        .user(userService.findById(Long.parseLong(values[5])))
                        .build();
                income.setId(Long.valueOf(values[0]));
                incomeService.save(income);
            });      
            log.debug("Incomes loaded...");
        }
    }
 
    private Stream<String> getStreamFromFile(Path path){
        try {
            return Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}