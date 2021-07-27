package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.database.RecipeCache
import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.CommonFlow
import de.darthkali.weefood.domain.util.DataState
import de.darthkali.weefood.domain.util.asCommonFlow
import de.darthkali.weefood.util.Logger
import kotlinx.coroutines.flow.flow


class   SearchIngredient(
    private val ingredientService: IngredientService,
    private val recipeCache: RecipeCache,
){
    private val logger = Logger("SearchRecipes")

    fun execute(
        query: String,
        page: Int
    ): CommonFlow<DataState<List<Ingredient>>> = flow  {
        try{
            emit(DataState.loading())

            // just to show pagination, api is fast
            //delay(500)

            // force error for testing
            if (query == "error") {
                throw Exception("Forcing an error... Search FAILED!")
            }

            val ingredient = ingredientService.search(
                query = query,
                page = page,
            )

            //TODO: add cache
            // use the recipe form the service and
//            // insert into cache
//            recipeCache.insert(recipes)
//
//            // query the cache
//            val cacheResult = if (query.isBlank()) {
//                recipeCache.getAll(page = page)
//            } else {
//                recipeCache.search(
//                    query = query,
//                    page = page,
//                )
//            }
            // emit List<Recipe> from cache
            emit(DataState.data(data = ingredient)) //TODO: changed from cacheResult to ingredient -> change after added cache
        } catch (e: Exception) {
            logger.log(e.toString())
        }
    }.asCommonFlow()

}