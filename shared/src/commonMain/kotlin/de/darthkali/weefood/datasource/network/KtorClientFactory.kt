package de.darthkali.weefood.datasource.network

import de.darthkali.weefood.datasource.network.model.RecipeDto
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}

fun RecipeDto.toRecipe(): Recipe{
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = datetimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = datetimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}