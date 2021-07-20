package de.darthkali.weefood.presentation.recipe_list


import de.darthkali.weefood.domain.model.GenericMessageInfo
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.Queue

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf(),
    val selectedCategory: FoodCategory? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
)  {
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        recipes = listOf(),
        selectedCategory = null,
        queue = Queue(mutableListOf()),
    )

}