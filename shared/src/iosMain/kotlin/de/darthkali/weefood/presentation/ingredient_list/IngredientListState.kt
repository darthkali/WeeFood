package de.darthkali.weefood.presentation.ingredient_list


import de.darthkali.weefood.datasource.database.model.IngredientDb

actual data class IngredientListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val ingredientDbs: List<IngredientDb> = listOf(),
    val bottomIngredientDb: IngredientDb? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
)  {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        ingredientDbs = listOf(),
        bottomIngredientDb = null,
    )

    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}