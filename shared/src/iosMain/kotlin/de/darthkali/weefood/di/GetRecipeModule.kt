package de.darthkali.weefood.di

import de.darthkali.weefood.interactors.recipe_detail.GetRecipe

class GetRecipeModule(
    private val cacheModule: CacheModule,
) {

    val getRecipe: GetRecipe by lazy{
        GetRecipe(
            recipeCache = cacheModule.recipeCache
        )
    }
}