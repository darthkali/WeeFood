package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.domain.model.Ingredient

interface IngredientService {

    suspend fun search(
        query: String,
        page: Int,
    ): List<Ingredient>
}