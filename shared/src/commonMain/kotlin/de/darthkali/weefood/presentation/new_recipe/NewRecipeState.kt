package de.darthkali.weefood.presentation.new_recipe

import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb

data class NewRecipeState(

    val isLoading: Boolean = false,

    val recipeDb: RecipeDb = RecipeDb(
        name = "",
        image = "",
        cooking_time = 0,
        cooking_time_unit = "",
        description = ""
    ),
    val recipeIngredientDbs: List<RecipeIngredientDb> = listOf(),
) {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor() : this(
        isLoading = false,
        recipeDb = RecipeDb(
            name = "",
            image = "",
            cooking_time = 0,
            cooking_time_unit = "",
            description = ""
        ),
        recipeIngredientDbs = listOf(),
    )
}