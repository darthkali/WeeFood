package de.darthkali.weefood.datasource.database.queries.ingredient

import de.darthkali.weefood.datasource.database.model.IngredientDb

interface IngredientQueries {
    fun insertIngredient(ingredientDb: IngredientDb): Int?
    fun updateIngredientByApiId(ingredientDb: IngredientDb): Int?
    fun getAllIngredients(): List<IngredientDb>
    fun getIngredientById(ingredientId: Int): IngredientDb?
    fun getIngredientByApiId(apiId: Int): IngredientDb?
    fun getLastInsertRowId(): Int?
    fun deleteIngredientById(ingredientId: Int): Boolean
    fun deleteAllIngredients(): Boolean
}