package de.darthkali.weefood.presentation.recipe_detail

import de.darthkali.weefood.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
){
    constructor(): this(
        isLoading = false,
        recipe = null,
        //queue = Queue(mutableListOf())
    )
}
