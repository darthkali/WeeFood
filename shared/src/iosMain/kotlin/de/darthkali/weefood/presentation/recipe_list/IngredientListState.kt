package de.darthkali.weefood.presentation.recipe_list


import de.darthkali.weefood.domain.model.GenericMessageInfo
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.Queue

actual data class IngredientListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val ingredients: List<Ingredient> = listOf(),
    val bottomIngredient: Ingredient? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
    //val selectedCategory: FoodCategory? = null,
//    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
)  {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        ingredients = listOf(),
        bottomIngredient = null,
//        selectedCategory = null,
//        queue = Queue(mutableListOf()),
    )

    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}