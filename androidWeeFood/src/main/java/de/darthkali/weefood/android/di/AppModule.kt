package de.darthkali.weefood.android.di

import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListViewModel
import de.darthkali.weefood.android.presentation.screens.new_recipe.NewRecipeViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    val appModule = module {
        viewModel { IngredientListViewModel() }
        viewModel { RecipeListViewModel() }
        viewModel { NewRecipeViewModel() }
    }
