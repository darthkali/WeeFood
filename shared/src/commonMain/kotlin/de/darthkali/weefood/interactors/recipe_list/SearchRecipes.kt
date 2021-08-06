package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueries
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.model.Recipe
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