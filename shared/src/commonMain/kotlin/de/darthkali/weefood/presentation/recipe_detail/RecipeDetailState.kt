package de.darthkali.weefood.presentation.recipe_detail

import de.darthkali.weefood.domain.model.GenericMessageInfo
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val ingredient: Ingredient? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
){
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        ingredient = null,
        queue = Queue(mutableListOf()),
    )

}
