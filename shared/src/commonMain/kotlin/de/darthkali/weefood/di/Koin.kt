package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.WeeFoodDatabaseFactory
import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
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

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(arrayListOf(
            Modules.network,
            Modules.database,
            Modules.interactor
        ))
    }

// called by iOS
fun initKoin() = initKoin() {}




