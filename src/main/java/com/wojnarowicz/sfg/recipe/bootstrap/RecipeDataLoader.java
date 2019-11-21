package com.wojnarowicz.sfg.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.domain.Difficulty;
import com.wojnarowicz.sfg.recipe.domain.Ingredient;
import com.wojnarowicz.sfg.recipe.domain.Notes;
import com.wojnarowicz.sfg.recipe.domain.Recipe;
import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;
import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;
import com.wojnarowicz.sfg.recipe.service.RecipeCategoryService;
import com.wojnarowicz.sfg.recipe.service.RecipeService;
import com.wojnarowicz.sfg.recipe.service.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RecipeDataLoader implements ApplicationListener<ContextRefreshedEvent>{

    private RecipeCategoryService recipeCategoryService;
    private UnitOfMeasureService unitOfMeasureService;
    private RecipeService recipeService;
    
    private final UnitOfMeasure tablespoon;
    private final UnitOfMeasure teaspoon;
    private final UnitOfMeasure piece;
    
    private final Map<String, RecipeCategory> categories;
    
    @Autowired
    public RecipeDataLoader(RecipeService recipeService, RecipeCategoryService recipeCategoryService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.recipeCategoryService = recipeCategoryService;
        this.unitOfMeasureService = unitOfMeasureService;
        
        tablespoon = this.unitOfMeasureService.findByName("Tablespoon");
        teaspoon = this.unitOfMeasureService.findByName("Teaspoon");
        piece = this.unitOfMeasureService.findByName("Piece");
        
        this.categories = new HashMap<>();
        this.recipeCategoryService.findAll().forEach(category -> {
            this.categories.put(category.getName(), category);            
        });
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("RecipeDataLoader: loading data...");
        List<Recipe> recipes = new ArrayList<>();
        
        Recipe guacamole = createGuacamole();
        recipes.add(guacamole);
        
        Recipe tacos = createTacos();
        recipes.add(tacos);
        
        recipeService.saveAll(recipes);
    }

    private Recipe createGuacamole() {
        
        Recipe guacamole = Recipe.builder()
                .name("Perfect Guacamole")
                .source("perfect_guacamole")
                .title("How to Make Perfect Guacamole")
                .description("The BEST guacamole! EASY to make with ripe avocados, salt, serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips.")
                .cookTime(5)
                .difficulty(Difficulty.EASY)
                .prepTime(10)
                .servings(4)
                .url("https://www.simplyrecipes.com/recipes/perfect_guacamole/")
                .directions("" + 
                        "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n"+
                        "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" + 
                        "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
                        "\r\n" + 
                        "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n" + 
                        "\r\n" + 
                        "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." + 
                        "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\r\n" + 
                        "\r\n" + 
                        "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" + 
                        "Variations\r\n" + 
                        "\r\n" + 
                        "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\r\n" + 
                        "\r\n" + 
                        "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\r\n" + 
                        "\r\n" + 
                        "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\r\n" + 
                        "\r\n" + 
                        "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\r\n" + 
                        "\r\n" + 
                        "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!")
                .build();

        Notes notes = new Notes();
        notes.setNotes("MAKING GUACAMOLE IS EASY\r\n" +
                       "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. You can get creative with homemade guacamole!\n" +
                       "GUACAMOLE TIP: USE RIPE AVOCADOS\n" +
                       "The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n" + 
                       "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n");
        notes.setRecipe(guacamole);
        guacamole.setNotes(notes);
        
        Ingredient ing1 = Ingredient.builder().name("Ripe Avocados").amount(new BigDecimal(2)).uom(piece).recipe(guacamole).build();
        Ingredient ing2 = Ingredient.builder().name("Kosher Salt").amount(new BigDecimal(0.5)).uom(tablespoon).recipe(guacamole).build();
        
        guacamole.addIngredient(ing1);
        guacamole.addIngredient(ing2);
        
        guacamole.addCategory(categories.get("Mexican"));
        guacamole.addCategory(categories.get("Vegan"));
        guacamole.addCategory(categories.get("Avocado"));
        guacamole.addCategory(categories.get("Guacamole"));
        
        return guacamole;
    }
    
    private Recipe createTacos() {
    	Recipe tacos = Recipe.builder()
                .name("Spicy Grilled Chicken Tacos")                
                .cookTime(9)
                .difficulty(Difficulty.MODERATE)
                .prepTime(20)
                .servings(4)
                .url("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/")
                .build();

    	Notes notes = new Notes();
    	notes.setNotes("Notes");
    	notes.setRecipe(tacos);
    	tacos.setNotes(notes);
    	
    	tacos.addIngredient(Ingredient.builder().name("Ancho Chili Powder").amount(new BigDecimal(2)).uom(tablespoon).recipe(tacos).build());
    	tacos.addIngredient(Ingredient.builder().name("Dried Oregano").amount(new BigDecimal(1)).uom(teaspoon).recipe(tacos).build());
        
    	tacos.addCategory(categories.get("American"));
    	tacos.addCategory(categories.get("Mexiac"));
    	return tacos;
    }
}
