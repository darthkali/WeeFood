package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.cache.RecipeCache
import de.darthkali.weefood.datasource.network.RecipeService
import de.darthkali.weefood.domain.model.GenericMessageInfo
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.CommonFlow
import de.darthkali.weefood.domain.util.DataState
import de.darthkali.weefood.domain.util.asCommonFlow
import de.darthkali.weefood.shared.domain.util.UIComponentType
import de.darthkali.weefood.util.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
){
    private val logger = Logger("SearchRecipes")

    fun execute(
        page: Int,
        query: String,
    ): CommonFlow<DataState<List<Recipe>>> = flow  {
        try{
            emit(DataState.loading())

            // just to show pagination, api is fast
            delay(500)

            // force error for testing
            if (query == "error") {
                throw Exception("Forcing an error... Search FAILED!")
            }

            val recipes = recipeService.search(
                page = page,
                query = query,
            )
            // insert into cache
            recipeCache.insert(recipes)

            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(
                    query = query,
                    page = page,
                )
            }
            // emit List<Recipe> from cache
            emit(DataState.data<List<Recipe>>(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message?: "Unknown Error")
            ))
        }
    }.asCommonFlow()

}