package de.darthkali.weefood.presentation.recipe_list

import de.darthkali.weefood.domain.model.Recipe

// TODO JavaDoc einfügen - muss als actual definiert werden, da bei ios eine weitere variable für die paginatipon nötig ist
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
