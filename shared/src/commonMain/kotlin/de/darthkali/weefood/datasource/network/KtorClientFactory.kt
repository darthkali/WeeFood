package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.datasource.network.model.RecipeDTO
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.DatetimeUtil
import io.ktor.client.*

fun RecipeDTO.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        publisher = publisher,
        featuredImage = featuredImage,
        rating = rating,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = datetimeUtil.toLocalDate(dateAdded.toDouble()),
        dateUpdated = datetimeUtil.toLocalDate(dateUpdated.toDouble()),
    )
}

fun List<RecipeDTO>.toRecipeList():List<Recipe>{
    return map { it.toRecipe() }
}

expect class KtorClientFactory() {
    fun build(): HttpClient
}