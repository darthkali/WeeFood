package de.darthkali.weefood.presentation.ingredient_list


import de.darthkali.weefood.domain.model.Ingredient

actual data class IngredientListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val ingredients: List<Ingredient> = listOf(),
    //val selectedCategory: FoodCategory? = null,
    //val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
)  {
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        ingredients = listOf(),
        //selectedCategory = null,
        //queue = Queue(mutableListOf()),
    )

}