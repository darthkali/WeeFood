package de.darthkali.weefood.datasource.database.repository.recipeIngredient

import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb

interface RecipeIngredientRepository {
    fun insertRecipeIngredient(recipeIngredientDb: RecipeIngredientDb): Int?
    fun updateRecipeIngredient(recipeIngredientDb: RecipeIngredientDb): Int?
    fun getAllRecipeIngredientByRecipeId(recipeId: Int): List<RecipeIngredientDb>
    fun getAllRecipeIngredients(): List<RecipeIngredientDb>
    fun deleteRecipeIngredientById(recipeId: Int): Boolean
    fun deleteRecipeIngredientByRecipeIdAndIngredientId(
        recipeDbId: Int,
        ingredientDbId: Int
    ): Boolean

    fun deleteAllRecipeIngredients(): Boolean
    fun deleteAllRecipeIngredientsByRecipeId(recipeId: Int): Boolean
}
