package com.wojnarowicz.sfg.recipe.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.recipe.command.RecipeCategoryCommand;
import com.wojnarowicz.sfg.recipe.domain.RecipeCategory;

import lombok.Synchronized;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class RecipeCategoryCommandToRecipeCategory implements Converter<RecipeCategoryCommand, RecipeCategory>{

    @Synchronized
    @Nullable
    @Override
    public RecipeCategory convert(RecipeCategoryCommand source) {
        if (source == null) {
            return null;
        }

        final RecipeCategory category = new RecipeCategory();
        category.setId(source.getId());
        category.setName(source.getName());
        return category;
    }
}
