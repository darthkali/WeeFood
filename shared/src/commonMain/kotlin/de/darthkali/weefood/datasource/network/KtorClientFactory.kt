package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.datasource.network.model.IngredientDto
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}

fun IngredientDto.toIngredient(): Ingredient{
    return Ingredient(
        id = id,
        name = name,
        image = image
    )
}

fun List<IngredientDto>.toIngredientList(): List<Ingredient>{
    return map{it.toIngredient()}
}