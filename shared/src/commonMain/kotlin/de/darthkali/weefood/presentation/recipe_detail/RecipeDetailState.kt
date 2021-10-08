package de.darthkali.weefood.presentation.recipe_detail

import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.domain.model.Recipe

data class RecipeDetailState(
    var changed: Int = 0,

    var recipe: Recipe = Recipe(
        databaseId = 0,
        name = "",
        image = "",
        cooking_time = 0,
        cooking_time_unit = "",
        recipeDescription = "",
        portion = 0,
        ingredients = listOf(),
    )
) {
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor() : this(
        recipe = Recipe(
            databaseId = 0,
            name = "",
            image = "",
            cooking_time = 0,
            cooking_time_unit = "",
            recipeDescription = "",
            portion = 0,
            ingredients = listOf()
        )
    )
}
