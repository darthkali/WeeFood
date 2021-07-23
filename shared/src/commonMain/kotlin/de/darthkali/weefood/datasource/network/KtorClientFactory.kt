package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.datasource.network.model.IngredientDto
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}



fun IngredientDto.toRecipe(): Recipe{
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = id,
        name = name,
        image = image,
        aisle = aisle,
        possibleUnits = possibleUnits,
    )
}

fun List<IngredientDto>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}