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

class SaveIngredient : KoinComponent {

    private val ingredientDb: IngredientDb by inject()
    private val logger = Logger("SaveIngredient")

    fun execute(
        ingredient: Ingredient,
    ): CommonFlow<DataState<Boolean>> = flow {
        try {
            emit(DataState.loading())
            emit(DataState.data(data = ingredientDb.insertIngredient(ingredient)))
        } catch (e: Exception) {
            logger.log(e.toString())
        }
    }.asCommonFlow()
}
