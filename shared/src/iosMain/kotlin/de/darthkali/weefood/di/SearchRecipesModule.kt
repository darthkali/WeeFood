package de.darthkali.weefood.di

import de.darthkali.weefood.interactors.recipe_list.SearchRecipes


class SearchRecipesModule(
    val networkModule: NetworkModule,
    val cacheModule: CacheModule,
) {

    val searchRecipes: SearchRecipes by lazy{
        SearchRecipes(
            recipeService = networkModule.recipeService,
            recipeCache = cacheModule.recipeCache
        )
    }

}