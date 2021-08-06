package de.darthkali.weefood.presentation.recipe_list

import de.darthkali.weefood.datasource.database.model.RecipeDb

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipeDbs: List<RecipeDb> = listOf(),
)  {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        recipeDbs = listOf(),
    )
}