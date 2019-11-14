package com.wojnarowicz.sfg.recipe.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.domain.Difficulty;
import com.wojnarowicz.sfg.recipe.domain.Ingredient;
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
    
    @Autowired
    public RecipeDataLoader(RecipeService recipeService, RecipeCategoryService recipeCategoryService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.recipeCategoryService = recipeCategoryService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("RecipeDataLoader: loading data...");
        Recipe guacamole = createGuacamole();
        recipeService.save(guacamole);
    }

    private Recipe createGuacamole() {
        UnitOfMeasure tablespoon = unitOfMeasureService.findByName("Tablespoon");
        UnitOfMeasure piece = unitOfMeasureService.findByName("Piece");
        
        RecipeCategory mexican = recipeCategoryService.findByName("Mexican");
        
        Recipe guacamole = new Recipe("Perfect Guacamole");
        guacamole.setCookTime(10);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setPrepTime(10);
        guacamole.setServings(4);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        guacamole.setDirections("" + 
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
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
        
        guacamole.addIngredient(new Ingredient("Ripe Avocados", new BigDecimal(2), piece, guacamole));
        guacamole.addIngredient(new Ingredient("Kosher Salt", new BigDecimal(0.5), tablespoon, guacamole));
                
        guacamole.addCategory(mexican);
        
        return guacamole;
    }
}
