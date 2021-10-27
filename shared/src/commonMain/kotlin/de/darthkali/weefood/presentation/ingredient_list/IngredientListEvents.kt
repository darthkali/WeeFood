package de.darthkali.weefood.presentation.ingredient_list

import de.darthkali.weefood.domain.model.Ingredient

sealed class IngredientListEvents {
    object LoadIngredient : IngredientListEvents()
    object NewSearch : IngredientListEvents()
    object NextPage : IngredientListEvents()
    data class SaveIngredient(val ingredient: Ingredient) : IngredientListEvents()
    data class OnUpdateQuery(val query: String) : IngredientListEvents()
}
