package de.darthkali.weefood.presentation.new_recipe

import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.model.RecipeIngredient

data class NewRecipeState(

    val isLoading: Boolean = false,
    val name: String = "",
    val image: String = "",
    val cooking_time: Int = 0,
    val cooking_time_unit: String = "",
    val description: String = "",

    val recipeIngredients: List<RecipeIngredient> = listOf(),
) {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor() : this(
        isLoading = false,
        name = "",
        image = "",
        cooking_time = 0,
        cooking_time_unit = "",
        description = "",
        recipeIngredients = listOf(),
    )
}