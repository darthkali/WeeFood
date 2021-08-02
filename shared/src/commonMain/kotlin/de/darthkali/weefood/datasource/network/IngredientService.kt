package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.domain.model.Ingredient

interface IngredientService {

    suspend fun searchIngredient(
        query: String,
        page: Int,
    ): List<Ingredient>

     fun searchIngredientBla(): String
}