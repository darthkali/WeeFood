package de.darthkali.weefood.presentation.recipe_detail

import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.model.Recipe

sealed class RecipeDetailEvents {
    object OnSaveRecipe : RecipeDetailEvents()
    data class OnUpdateName(val name: String) : RecipeDetailEvents()
    data class OnUpdateImage(val image: String) : RecipeDetailEvents()
    data class OnUpdateCookingTime(val cooking_time: Int) : RecipeDetailEvents()
    data class OnUpdateCookingTimeUnit(val cooking_time_unit: String) : RecipeDetailEvents()
    data class OnUpdateDescription(val description: String) : RecipeDetailEvents()
    data class OnUpdateIngredientQuantity(val ingredientId: Int, val quantity: Float) :
        RecipeDetailEvents()
    data class OnUpdateIngredientQuantityUnit(val ingredientId: Int, val quantityUnit: String) :
        RecipeDetailEvents()
    data class OnAddIngredient(val recipe: Recipe) :
        RecipeDetailEvents()
    data class OnDeleteIngredient(val ingredient: Ingredient) : RecipeDetailEvents()
    data class OnDeleteRecipe(val recipe: Recipe) : RecipeDetailEvents()
    data class GetRecipe(val recipeId: Int) : RecipeDetailEvents()
}
