package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveIngredient: KoinComponent{

    private val ingredientDb: IngredientDb by inject()
    private val logger = Logger("SaveIngredient")

    fun saveIngredient(
        ingredient: Ingredient,
    ) {
        try {
            ingredientDb.insertIngredient(ingredient)
        } catch (e: Exception) {
            logger.log(e.toString())
        }
    }
}