package de.darthkali.weefood.presentation.recipe_list

import de.darthkali.weefood.datasource.database.model.RecipeDb

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipeDbs: List<RecipeDb> = listOf(),
    val bottomRecipeDb: RecipeDb? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
)  {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        recipeDbs = listOf(),
        bottomRecipeDb = null,
    )

    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}