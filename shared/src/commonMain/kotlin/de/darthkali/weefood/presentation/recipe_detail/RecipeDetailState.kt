package de.darthkali.weefood.presentation.recipe_detail

import de.darthkali.weefood.datasource.database.model.IngredientDb

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val ingredientDb: IngredientDb? = null,
){
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        ingredientDb = null,
    )

}
