package de.darthkali.weefood.android.di

import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListViewModel
import de.darthkali.weefood.android.presentation.screens.new_recipe.NewRecipeViewModel
import de.darthkali.weefood.android.presentation.screens.new_recipe.RecipeDetailViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import io.ktor.http.parametersOf
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { IngredientListViewModel(get()) }
    viewModel { RecipeListViewModel(get()) }
    viewModel { NewRecipeViewModel(get()) }
    viewModel { RecipeDetailViewModel(get()) }
}
