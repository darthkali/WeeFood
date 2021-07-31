package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.util.Logger

class SaveIngredient(
    private val ingredientDb: IngredientDb,
) {
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

    fun getAll(
    ): List<Ingredient> {
        return try {
            ingredientDb.getAllIngredients()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

}