package de.darthkali.weefood.di

import de.darthkali.weefood.interactors.recipe_list.SearchIngredient


class SearchRecipesModule(
    val networkModule: NetworkModule,
    val cacheModule: CacheModule,
) {

    val searchIngredient: SearchIngredient by lazy{
        SearchIngredient(
            ingredientService = networkModule.ingredientService,
            recipeCache = cacheModule.recipeCache
        )
    }

}