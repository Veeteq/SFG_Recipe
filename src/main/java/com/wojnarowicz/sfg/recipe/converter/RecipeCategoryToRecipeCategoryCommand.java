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
public class RecipeCategoryToRecipeCategoryCommand implements Converter<RecipeCategory, RecipeCategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public RecipeCategoryCommand convert(RecipeCategory source) {
        if (source == null) {
            return null;
        }

        final RecipeCategoryCommand categoryCommand = new RecipeCategoryCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setName(source.getName());

        return categoryCommand;
    }
}
