package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.database.RecipeCache
import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.CommonFlow
import de.darthkali.weefood.domain.util.DataState
import de.darthkali.weefood.domain.util.asCommonFlow
import de.darthkali.weefood.util.Logger
import kotlinx.coroutines.flow.flow


class SearchIngredient(
    private val ingredientService: IngredientService,
    private val ingredientDb: IngredientDb,
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
//            if (query == "error") {
//                throw Exception("Forcing an error... Search FAILED!")
//            }

            val ingredientList = ingredientService.search(
                query = query,
                page = page,
            )

//            //TODO: add cache
//            // use the recipe form the service and
//            // insert into cache
//            ingredientDb.insertIngredient(recipes)
//
//            // query the cache
//            val cacheResult = if (query.isBlank()) {
//                ingredientDb.getAll(page = page)
//            } else {
//                ingredientDb.search(
//                    query = query,
//                    page = page,
//                )
//            }
            // emit List<Recipe> from cache
            emit(DataState.data(data = ingredientList)) //TODO: changed from cacheResult to ingredient -> change after added cache
        } catch (e: Exception) {
            logger.log(e.toString())
        }
    }.asCommonFlow()

}