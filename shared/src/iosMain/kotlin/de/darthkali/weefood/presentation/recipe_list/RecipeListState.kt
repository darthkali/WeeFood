package de.darthkali.weefood.presentation.recipe_list

import de.darthkali.weefood.domain.model.Recipe

// TODO JavaDoc einfügen - bottomIngredient ist extra
actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf(),
    val bottomRecipe: Recipe? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
) {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor() : this(
        isLoading = false,
        page = 1,
        query = "",
        recipes = listOf(),
        bottomRecipe = null,
    )

    companion object {
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}
