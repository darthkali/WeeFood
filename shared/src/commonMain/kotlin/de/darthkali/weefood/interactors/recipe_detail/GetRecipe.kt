package de.darthkali.weefood.interactors.recipe_detail

import de.darthkali.weefood.datasource.cache.RecipeCache
import de.darthkali.weefood.domain.model.GenericMessageInfo
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.CommonFlow
import de.darthkali.weefood.domain.util.DataState
import de.darthkali.weefood.domain.util.asCommonFlow
import de.darthkali.weefood.shared.domain.util.UIComponentType
import de.darthkali.weefood.util.BuildConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe (
    private val recipeCache: RecipeCache,
){

    fun execute(
        recipeId: Int,
    ): CommonFlow<DataState<Ingredient>> = flow {
        try {
            emit(DataState.loading())

            // just to show loading, cache is fast
            // Note: iOS loads the DetailView ahead of time so delaying here for iOS is pointless
            if(BuildConfig().isDebug() && BuildConfig().isAndroid()){
                delay(500)
            }

            // Force error for testing
            if(recipeId == 1){
                throw Exception("Invalid Recipe Id")
            }

            val recipe =  recipeCache.get(recipeId)

            emit(DataState.data(message = null, data = recipe))

        }catch (e: Exception){
            emit(DataState.error<Ingredient>(
                message = GenericMessageInfo.Builder()
                    .id("GetRecipe.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message?: "Unknown Error")
            ))
        }
    }.asCommonFlow()


}