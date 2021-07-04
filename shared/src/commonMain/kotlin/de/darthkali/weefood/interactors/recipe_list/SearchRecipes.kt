package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.cache.RecipeCache
import de.darthkali.weefood.datasource.network.RecipeService
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow  {
        emit(DataState.loading())
        try{
            val recipes = recipeService.search(
                page = page,
                query = query,
            )

            // delay 500ms so we can see loading
            delay(500) //TODO: Delete, its jut for testing

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
        }catch (e: Exception){
            emit(DataState.error<List<Recipe>>(message = e.message?: "Unknown Error"))
        }
    }
}