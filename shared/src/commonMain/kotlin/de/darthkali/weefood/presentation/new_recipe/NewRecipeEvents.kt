package de.darthkali.weefood.presentation.new_recipe

import de.darthkali.weefood.domain.model.Recipe

sealed class NewRecipeEvents{
    data class OnUpdateName(val name: String): NewRecipeEvents()
    data class OnUpdateImage(val image: String): NewRecipeEvents()
    data class OnUpdateCookingTime(val cooking_time: Int): NewRecipeEvents()
    data class OnUpdateCookingTimeUnit(val cooking_time_unit: String): NewRecipeEvents()
    data class OnUpdateDescription(val description: String): NewRecipeEvents()
    data class OnSaveRecipe(val recipe: Recipe): NewRecipeEvents()
    data class GetRecipe(val recipeId: Int): NewRecipeEvents()

}