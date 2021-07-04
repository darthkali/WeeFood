package de.darthkali.weefood.presentation.recipe_detail

import de.darthkali.weefood.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
)
