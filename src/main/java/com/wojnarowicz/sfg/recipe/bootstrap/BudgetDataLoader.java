package com.wojnarowicz.sfg.recipe.bootstrap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.domain.Category;
import com.wojnarowicz.sfg.recipe.domain.CategoryType;
import com.wojnarowicz.sfg.recipe.domain.Item;
import com.wojnarowicz.sfg.recipe.domain.User;
import com.wojnarowicz.sfg.recipe.service.CategoryService;
import com.wojnarowicz.sfg.recipe.service.ItemService;
import com.wojnarowicz.sfg.recipe.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BudgetDataLoader implements CommandLineRunner {

    private final static Path baseDirectory = Paths.get("C:\\Users\\la289dm\\Downloads\\incomes");
    
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    
    @Autowired
    public BudgetDataLoader(CategoryService categoryService, ItemService itemService, UserService userService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadItems();
        loadUsers();
    }

    private void loadCategories() {
        Path path = baseDirectory.resolve("categories.txt");
    	//String file = "F:\\categories.txt";
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            Category category = new Category();
            category.setId(Long.valueOf(values[0]));
            category.setName(values[1]);
            category.setType(CategoryType.valueOf(values[2]));
            categoryService.save(category);
        });      
        log.debug("Categories loaded...");
    }

    private void loadItems() {
        Path path = baseDirectory.resolve("items.txt");
        //String file = "F:\\categories.txt";
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            Item item = new Item();
            item.setId(Long.valueOf(values[0]));
            item.setCategory(categoryService.findById(Long.parseLong(values[1])));
            item.setName(values[2]);
            itemService.save(item);
        });      
        log.debug("Items loaded...");
    }

    private void loadUsers() {
        Path path = baseDirectory.resolve("users.txt");
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            User user = new User();
            user.setId(Long.valueOf(values[0]));
            user.setName(values[1]);
            userService.save(user);
        });      
        log.debug("Users loaded...");
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