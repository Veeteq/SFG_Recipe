package com.wojnarowicz.sfg.recipe.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;
import com.wojnarowicz.sfg.recipe.domain.UnitOfMeasure;
import com.wojnarowicz.sfg.recipe.repository.RecipeCategoryRepository;
import com.wojnarowicz.sfg.recipe.repository.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile(value = {"h2file","mysql","oracle"})
@Slf4j
public class RecipeDataLoader01 implements ApplicationListener<ContextRefreshedEvent>{
	
	private final RecipeCategoryRepository recipeCategoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Autowired
	public RecipeDataLoader01(RecipeCategoryRepository recipeCategoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.recipeCategoryRepository = recipeCategoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(recipeCategoryRepository.count() == 0) {
			log.debug("Loading categories");
			loadCategories();
		}
		
		if(unitOfMeasureRepository.count() == 0) {
			log.debug("Loading Units of Measure");
			loadUnitsOfMeasure();
		}
		
	}

	private void loadCategories() {
		recipeCategoryRepository.save(RecipeCategory.builder().name("American").build());
		recipeCategoryRepository.save(RecipeCategory.builder().name("Italian").build());
		recipeCategoryRepository.save(RecipeCategory.builder().name("Mexican").build());
		recipeCategoryRepository.save(RecipeCategory.builder().name("Thai").build());
		recipeCategoryRepository.save(RecipeCategory.builder().name("Vegan").build());
		recipeCategoryRepository.save(RecipeCategory.builder().name("Avocado").build());
		recipeCategoryRepository.save(RecipeCategory.builder().name("Guacamole").build());
	}

	private void loadUnitsOfMeasure() {
		unitOfMeasureRepository.save(UnitOfMeasure.builder().name("Teaspoon").build());
		unitOfMeasureRepository.save(UnitOfMeasure.builder().name("Tablespoon").build());
		unitOfMeasureRepository.save(UnitOfMeasure.builder().name("Cup").build());
		unitOfMeasureRepository.save(UnitOfMeasure.builder().name("Pinch").build());
		unitOfMeasureRepository.save(UnitOfMeasure.builder().name("Ounce").build());
		unitOfMeasureRepository.save(UnitOfMeasure.builder().name("Piece").build());
	}
}
