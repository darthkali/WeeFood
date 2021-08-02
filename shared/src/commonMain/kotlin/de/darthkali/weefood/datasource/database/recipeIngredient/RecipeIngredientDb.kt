package de.darthkali.weefood.datasource.database.recipeIngredient

import de.darthkali.weefood.domain.model.RecipeIngredient

interface RecipeIngredientDb {
    fun insertRecipeIngredient(recipeIngredient: RecipeIngredient): Boolean
    fun getAllRecipeIngredientByRecipeId(recipeId: Int): List<RecipeIngredient>
    fun getAllRecipeIngredients(): List<RecipeIngredient>
    fun deleteRecipeIngredientById(recipeId: Int): Boolean
    fun deleteAllRecipeIngredients(): Boolean
}