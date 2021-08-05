package de.darthkali.weefood.presentation.new_recipe

import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb
import de.darthkali.weefood.domain.model.Recipe

data class NewRecipeState(

    val isLoading: Boolean = false,

    val recipe: Recipe = Recipe(
        name = "",
        image = "",
        cooking_time = 0,
        cooking_time_unit = "",
        description = "",
        portion = 0,
        ingredients = listOf(),
    )
) {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor() : this(
        isLoading = false,

        recipe = Recipe(
            name = "",
            image = "",
            cooking_time = 0,
            cooking_time_unit = "",
            description = "",
            portion = 0,
            ingredients = listOf(),
        )
    )
}