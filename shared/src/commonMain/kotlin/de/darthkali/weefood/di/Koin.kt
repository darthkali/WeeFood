package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.repository.ingredient.IngredientRepository
import de.darthkali.weefood.datasource.database.repository.ingredient.IngredientRepositoryImpl
import de.darthkali.weefood.datasource.database.repository.recipe.RecipeRepository
import de.darthkali.weefood.datasource.database.repository.recipe.RecipeRepositoryImpl
import de.darthkali.weefood.datasource.database.repository.recipeIngredient.RecipeIngredientRepository
import de.darthkali.weefood.datasource.database.repository.recipeIngredient.RecipeIngredientRepositoryImpl
import de.darthkali.weefood.datasource.database.repository.weekRecipe.WeekRecipeRepository
import de.darthkali.weefood.datasource.database.repository.weekRecipe.WeekRecipeRepositoryImpl
import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.datasource.network.IngredientServiceImpl
import de.darthkali.weefood.datasource.network.KtorClientFactory
import de.darthkali.weefood.interactors.ingredient.GetAllIngredients
import de.darthkali.weefood.interactors.ingredient.SaveIngredient
import de.darthkali.weefood.interactors.ingredient.SearchIngredient
import de.darthkali.weefood.interactors.recipe.GetRecipe
import de.darthkali.weefood.interactors.recipe.SaveRecipe
import de.darthkali.weefood.interactors.recipe_ingredient.SaveRecipeIngredient
import de.darthkali.weefood.interactors.recipe.SearchRecipes
import de.darthkali.weefood.interactors.recipe_ingredient.DeleteRecipeIngredient
import de.darthkali.weefood.interactors.recipe_ingredient.GetIngredientsFromRecipe
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            network,
            database,
            interactor
        )
    }

// called by iOS
fun initKoin() = initKoin() {}

expect fun platformModule(): Module

val network = module {
    single { KtorClientFactory() }
    single<IngredientService> { IngredientServiceImpl() }
}

val database = module {
    single { WeeFoodDatabase(get()) }
    single<IngredientRepository> { IngredientRepositoryImpl() }
    single<RecipeRepository> { RecipeRepositoryImpl() }
    single<RecipeIngredientRepository> { RecipeIngredientRepositoryImpl() }
    single<WeekRecipeRepository> { WeekRecipeRepositoryImpl() }
}

val interactor = module {
    single { SearchIngredient() }
    single { SaveIngredient() }
    single { GetAllIngredients() }
    single { SaveRecipe() }
    single { SaveRecipeIngredient() }
    single { GetRecipe() }
    single { SearchRecipes() }
    single { DeleteRecipeIngredient() }
    single { GetIngredientsFromRecipe() }

}




