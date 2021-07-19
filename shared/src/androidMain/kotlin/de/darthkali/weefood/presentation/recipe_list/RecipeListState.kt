package de.darthkali.weefood.presentation.recipe_list


import de.darthkali.weefood.domain.model.Recipe

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    //val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
)