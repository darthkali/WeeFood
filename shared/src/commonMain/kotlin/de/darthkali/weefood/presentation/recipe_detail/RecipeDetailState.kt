package de.darthkali.weefood.presentation.recipe_detail

import de.darthkali.weefood.domain.model.GenericMessageInfo
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
){
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        recipe = null,
        queue = Queue(mutableListOf()),
    )

}
