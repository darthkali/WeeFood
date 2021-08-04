package de.darthkali.weefood.datasource.database.queries.ingredient

import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient

interface IngredientQueries {
    fun insertIngredient(ingredientDb: IngredientDb): Int?
    fun updateIngredient(ingredientDb: IngredientDb): Int?
    fun getAllIngredients(): List<IngredientDb>
    fun getIngredientById(ingredientId: Int): IngredientDb?
    fun getIngredientByApiId(apiId: Int): IngredientDb?
    fun getLastInsertRowId(): Int?
    fun deleteIngredientById(ingredientId: Int): Boolean
    fun deleteAllIngredients(): Boolean
}