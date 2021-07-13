package de.darthkali.weefood.interactors.recipe_detail

import de.darthkali.weefood.datasource.cache.RecipeCache
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.CommonFlow
import de.darthkali.weefood.domain.util.DataState
import de.darthkali.weefood.domain.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache,
) {
    fun execute(
        recipeId: Int,
    ): CommonFlow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())

            val recipe =  recipeCache.get(recipeId)

            delay(500)  //TODO: Delete, its jut for testing
            emit(DataState.data(message = null, data = recipe))

        }catch (e: Exception){
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown Error"))
        }
    }.asCommonFlow()
}