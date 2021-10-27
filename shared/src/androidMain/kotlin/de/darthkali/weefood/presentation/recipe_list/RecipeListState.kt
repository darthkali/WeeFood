package de.darthkali.weefood.presentation.recipe_list

import de.darthkali.weefood.domain.model.Recipe

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    var query: String = "",
    val recipes: List<Recipe> = listOf(),
) {
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor() : this(
        isLoading = false,
        page = 1,
        query = "",
        recipes = listOf(),
    )
}
