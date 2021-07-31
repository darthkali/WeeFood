package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllIngredients: KoinComponent {

    private val ingredientDb: IngredientDb by inject()
    private val logger = Logger("GetAllIngredients")


    fun getAllIngredients(
    ): List<Ingredient> {
        return try {
            ingredientDb.getAllIngredients()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }
}
