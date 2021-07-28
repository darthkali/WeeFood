package de.darthkali.weefood.datasource.database.ingredient

import de.darthkali.weefood.domain.model.Ingredient

interface IngredientDb {
    fun insertIngredient(ingredient: Ingredient): Boolean
    fun getAllIngredients(): List<Ingredient>
    fun searchIngredients(name: String): List<Ingredient>
    fun getIngredientById(ingredientId: Int): Ingredient?
    fun deleteIngredientById(ingredientId: Int): Boolean
    fun deleteAllIngredients(): Boolean
}