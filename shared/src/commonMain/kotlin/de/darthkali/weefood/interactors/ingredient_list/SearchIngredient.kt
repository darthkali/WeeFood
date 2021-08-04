package de.darthkali.weefood.interactors.ingredient_list

import de.darthkali.weefood.datasource.network.IngredientService
import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.CommonFlow
import de.darthkali.weefood.domain.util.DataState
import de.darthkali.weefood.domain.util.asCommonFlow
import de.darthkali.weefood.util.Logger
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchIngredient : KoinComponent {

    private val ingredientService: IngredientService by inject()
    private val logger = Logger("SearchIngredient")


    fun execute(
        query: String,
        page: Int
    ): CommonFlow<DataState<List<Ingredient>>> = flow {
        try {
            emit(DataState.loading())

            val ingredientList = ingredientService.searchIngredient(
                query = query,
                page = page,
            )
            emit(DataState.data(data = ingredientList))
        } catch (e: Exception) {
            logger.log(e.toString())
        }
    }.asCommonFlow()

}