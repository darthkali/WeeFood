package de.darthkali.weefood.datasource.database.repository.recipe

import de.darthkali.weefood.datasource.database.model.RecipeDb

interface RecipeRepository {
    fun insertRecipe(recipeDb: RecipeDb): Int?
    fun updateRecipe(recipeDb: RecipeDb): Int?
    fun getAllRecipes(): List<RecipeDb>
    fun getRecipeById(recipeId: Int): RecipeDb?
    fun searchRecipes(name: String, page: Int): List<RecipeDb>
    fun getLastInsertRowId(): Int?
    fun deleteRecipeById(recipeId: Int): Boolean
    fun deleteAllRecipes(): Boolean
}
