package de.darthkali.weefood.presentation.recipe_list


import de.darthkali.weefood.domain.model.Recipe

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val bottomRecipe: Recipe? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
    //val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
)  {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        recipes = listOf(),
        selectedCategory = null,
        bottomRecipe = null,
        //queue = Queue(mutableListOf()),
    )

    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}