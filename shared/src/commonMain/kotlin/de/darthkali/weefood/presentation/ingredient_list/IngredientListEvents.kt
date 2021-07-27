package de.darthkali.weefood.presentation.ingredient_list

sealed class IngredientListEvents{

    object LoadIngredient: IngredientListEvents()

    object NewSearch: IngredientListEvents()

    object NextPage: IngredientListEvents()

    data class OnUpdateQuery(val query: String): IngredientListEvents()

}