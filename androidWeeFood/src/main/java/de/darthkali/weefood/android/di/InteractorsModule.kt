package de.darthkali.weefood.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.darthkali.weefood.datasource.database.RecipeCache
import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.interactors.recipe_detail.GetRecipe
import de.darthkali.weefood.interactors.recipe_list.SearchIngredient
import io.ktor.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        ingredientService: IngredientService,
        recipeCache: RecipeCache,
    ): SearchIngredient {
        return SearchIngredient(
            ingredientService = ingredientService,
            recipeCache = recipeCache
        )
    }

    @InternalAPI
    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache,
    ): GetRecipe {
        return GetRecipe(
            recipeCache = recipeCache
        )
    }
}