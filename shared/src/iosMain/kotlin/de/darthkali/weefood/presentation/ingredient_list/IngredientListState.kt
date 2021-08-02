package de.darthkali.weefood.presentation.ingredient_list


import de.darthkali.weefood.domain.model.Ingredient

actual data class IngredientListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val ingredients: List<Ingredient> = listOf(),
    val bottomIngredient: Ingredient? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
)  {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        ingredients = listOf(),
        bottomIngredient = null,
    )

    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}