package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger

class GetAllIngredients(
    private val ingredientDb: IngredientDb,
) {
    private val logger = Logger("GetAllIngredients")


    fun GetAllIngredients(
    ): List<Ingredient> {
        return try {
            ingredientDb.getAllIngredients()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }
}
