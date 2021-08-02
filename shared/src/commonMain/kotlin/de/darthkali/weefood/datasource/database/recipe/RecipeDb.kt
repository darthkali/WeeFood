package de.darthkali.weefood.datasource.database.recipe

import de.darthkali.weefood.domain.model.Recipe

interface RecipeDb {
    fun insertRecipe(recipe: Recipe): Boolean
    fun getAllRecipes(): List<Recipe>
    fun getRecipeById(recipeId: Int): Recipe?
    fun searchRecipes(name: String): List<Recipe>
    fun deleteRecipeById(recipeId: Int): Boolean
    fun deleteAllRecipes(): Boolean
}
