package de.darthkali.weefood.presentation.recipe_list

import de.darthkali.weefood.presentation.ViewEvent

sealed class RecipeListEvents : ViewEvent {
    object LoadRecipe : RecipeListEvents()
    object NewSearch : RecipeListEvents()
    object NextPage : RecipeListEvents()
    data class OnUpdateQuery(val query: String) : RecipeListEvents()
}
