package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.datasource.network.model.IngredientDto
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}



fun IngredientDto.toRecipe(): Ingredient{
    val datetimeUtil = DatetimeUtil()
    return Ingredient(
        id = id,
        name = name,
        image = image,
        aisle = aisle,
        possibleUnits = possibleUnits,
    )
}

fun List<IngredientDto>.toRecipeList(): List<Ingredient>{
    return map{it.toRecipe()}
}