package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueriesImpl
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueries
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueriesImpl
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueriesImpl
import de.darthkali.weefood.datasource.database.queries.weekRecipe.WeekRecipeQueries
import de.darthkali.weefood.datasource.database.queries.weekRecipe.WeekRecipeQueriesImpl
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
    single<IngredientQueries> { IngredientQueriesImpl() }
    single<RecipeQueries> { RecipeQueriesImpl() }
    single<RecipeIngredientQueries> { RecipeIngredientQueriesImpl() }
    single<WeekRecipeQueries> { WeekRecipeQueriesImpl() }
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




