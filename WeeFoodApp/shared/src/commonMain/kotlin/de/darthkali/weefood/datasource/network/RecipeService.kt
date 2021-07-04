package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.domain.model.Recipe

interface RecipeService {

    suspend fun search(
        page: Int,
        query: String,
    ): List<Recipe>


    suspend fun get(
        id: Int,
    ): Recipe
}