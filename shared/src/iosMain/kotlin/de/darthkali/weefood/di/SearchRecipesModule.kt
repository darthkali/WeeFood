package de.darthkali.weefood.di

import de.darthkali.weefood.interactors.recipe_list.SearchRecipes


class SearchRecipesModule(
    private val networkModule: NetworkModule,
    private val cahceModule: CacheModule,
) {

    val searchRecipes: SearchRecipes by lazy {
        SearchRecipes(
            recipeService = networkModule.recipeService,
            recipeCache = cahceModule.recipeCache
        )
    }
}