package de.darthkali.weefood.presentation.recipe_list

import de.darthkali.weefood.datasource.database.model.RecipeDb

//TODO JavaDoc einfügen - muss als actual definiert werden, da bei ios eine weitere variable für die paginatipon nötig ist
actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    var query: String = "",
    val recipeDbs: List<RecipeDb> = listOf(),
) {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor() : this(
        isLoading = false,
        page = 1,
        query = "",
        recipeDbs = listOf(),
    )
}