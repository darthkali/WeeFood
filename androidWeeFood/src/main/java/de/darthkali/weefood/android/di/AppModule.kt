package de.darthkali.weefood.android.di

import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_detail.RecipeDetailViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { IngredientListViewModel(get()) }
    viewModel { RecipeListViewModel(get()) }
    viewModel { RecipeDetailViewModel(get()) }
}
