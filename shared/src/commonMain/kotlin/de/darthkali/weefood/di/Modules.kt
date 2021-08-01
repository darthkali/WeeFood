package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.datasource.database.ingredient.IngredientDbImpl
import de.darthkali.weefood.datasource.database.recipe.RecipeDb
import de.darthkali.weefood.datasource.database.recipe.RecipeDbImpl
import de.darthkali.weefood.datasource.database.recipeIngredient.RecipeIngredientDb
import de.darthkali.weefood.datasource.database.recipeIngredient.RecipeIngredientDbImpl
import de.darthkali.weefood.datasource.database.weekRecipe.WeekRecipeDb
import de.darthkali.weefood.datasource.database.weekRecipe.WeekRecipeDbImpl
import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.datasource.network.IngredientServiceImpl
import de.darthkali.weefood.datasource.network.KtorClientFactory
import de.darthkali.weefood.interactors.recipe_list.GetAllIngredients
import de.darthkali.weefood.interactors.recipe_list.SaveIngredient
import de.darthkali.weefood.interactors.recipe_list.SearchIngredient
import org.koin.dsl.module

object Modules {

    val network = module {
        single { KtorClientFactory() }
        single<IngredientService> { IngredientServiceImpl() }
    }

    val database = module {
        single { WeeFoodDatabase(get()) }
        single<IngredientDb> { IngredientDbImpl() }
        single<RecipeDb> { RecipeDbImpl() }
        single<RecipeIngredientDb> { RecipeIngredientDbImpl() }
        single<WeekRecipeDb> { WeekRecipeDbImpl() }
    }

    val interactor = module {
        single { SearchIngredient() }
        single { SaveIngredient() }
        single { GetAllIngredients() }

    }


}