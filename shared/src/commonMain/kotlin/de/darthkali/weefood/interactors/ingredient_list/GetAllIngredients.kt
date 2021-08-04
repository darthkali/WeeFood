package de.darthkali.weefood.interactors.ingredient_list

import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.CommonFlow
import de.darthkali.weefood.domain.util.DataState
import de.darthkali.weefood.domain.util.asCommonFlow
import de.darthkali.weefood.util.Logger
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllIngredients : KoinComponent {

    private val ingredientDb: IngredientDb by inject()
    private val logger = Logger("GetAllIngredients")

    fun execute(): CommonFlow<DataState<List<Ingredient>>> = flow {
        try {
            emit(DataState.loading())
            emit(DataState.data(data = ingredientDb.getAllIngredients()))
        } catch (e: Exception) {
            logger.log(e.toString())
        }
    }.asCommonFlow()
}

