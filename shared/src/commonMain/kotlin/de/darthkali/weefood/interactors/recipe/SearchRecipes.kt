package de.darthkali.weefood.interactors.recipe

import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueries
import de.darthkali.weefood.domain.util.CommonFlow
import de.darthkali.weefood.domain.util.DataState
import de.darthkali.weefood.domain.util.asCommonFlow
import de.darthkali.weefood.util.Logger
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchRecipes : KoinComponent {

    private val recipeQueries: RecipeQueries by inject()
    private val logger = Logger("SearchRecipe")

    /**
     * @param query: String
     * @param page: Int
     *
     * search recipe by name (query)
     * result depends on the page (pagination)
     * emits the result in a data object
     *
     * @return DataState
     */
    fun execute(
        query: String,
        page: Int
    ): CommonFlow<DataState<List<RecipeDb>>> = flow {
        try {
            emit(DataState.loading())

            val recipeList = recipeQueries.searchRecipes(
                name = query,
                page = page,
            )
            emit(DataState.data(data = recipeList))
        } catch (e: Exception) {
            logger.log(e.toString())
        }
    }.asCommonFlow()
}