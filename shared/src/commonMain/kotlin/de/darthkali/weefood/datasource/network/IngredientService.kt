package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.domain.model.Recipe

interface IngredientService {

    suspend fun search(
        query: String,
        page: Int,
    ): List<Recipe>

//    suspend fun get(
//        id: Int
//    ): Recipe
}