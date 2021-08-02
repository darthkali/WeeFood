package de.darthkali.weefood.presentation.recipe_detail

import de.darthkali.weefood.domain.model.Ingredient

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val ingredient: Ingredient? = null,
){
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        ingredient = null,
    )

}
