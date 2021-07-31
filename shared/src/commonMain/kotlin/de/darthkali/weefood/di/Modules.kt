package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.WeeFoodDatabaseFactory
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
import de.darthkali.weefood.interactors.recipe_list.SearchIngredient
import io.ktor.client.HttpClient
import org.koin.dsl.module

object Modules {

    val network = module {
        single { KtorClientFactory() }
        single<IngredientService> { IngredientServiceImpl() }
    }

    val database = module {
        single { WeeFoodDatabaseFactory(get()) } //TODO .createDatabase
        single<IngredientDb> { IngredientDbImpl(get()) }
        single<RecipeDb> { RecipeDbImpl(get()) }
        single<RecipeIngredientDb> { RecipeIngredientDbImpl(get()) }
        single<WeekRecipeDb> { WeekRecipeDbImpl(get()) }
    }

    val interactor = module {
        single { SearchIngredient(get()) }
    }
}