package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.cache.RecipeCache
import de.darthkali.weefood.datasource.network.RecipeService
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {

        emit(DataState.loading())

        try {
            val recipes = recipeService.search(
                page = page,
                query = query
            )

            recipeCache.insert(recipes)

            val cacheResult = if(query.isBlank()) {
                recipeCache.getAll(page = page)
            }else{
                recipeCache.search(
                    query = query,
                    page = page
                )
            }



            kotlinx.coroutines.delay(500) //just for test
            emit(DataState.data(data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(message = e.message?: "Unknown Error"))
        }
    }
}