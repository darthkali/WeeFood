package de.darthkali.weefood.presentation.ingredient_list

import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.presentation.ViewEvent

sealed class IngredientListEvents : ViewEvent {
    object LoadIngredient : IngredientListEvents()
    object NewSearch : IngredientListEvents()
    object NextPage : IngredientListEvents()
    data class SaveIngredient(val ingredient: Ingredient) : IngredientListEvents()
    data class OnUpdateQuery(val query: String) : IngredientListEvents()
}