package de.darthkali.weefood.di

import de.darthkali.weefood.datasource.network.KtorClientFactory
import de.darthkali.weefood.datasource.network.RecipeService
import de.darthkali.weefood.datasource.network.model.RecipeServiceImpl

class NetworkModule {

    val recipeService: RecipeService by lazy {
        RecipeServiceImpl(
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL
        )
    }
}