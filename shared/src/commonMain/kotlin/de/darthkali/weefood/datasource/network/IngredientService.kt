package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.datasource.network.model.IngredientDto

interface IngredientService {
    suspend fun searchIngredient(
        query: String,
        page: Int,
    ): List<IngredientDto>
}
