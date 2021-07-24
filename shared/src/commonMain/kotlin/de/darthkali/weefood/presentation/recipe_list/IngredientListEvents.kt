package de.darthkali.weefood.presentation.recipe_list

sealed class IngredientListEvents{

    object LoadIngredient: IngredientListEvents()

    object NewSearch: IngredientListEvents()

    object NextPage: IngredientListEvents()

    data class OnUpdateQuery(val query: String): IngredientListEvents()

    data class OnSelectCategory(val category: FoodCategory): IngredientListEvents()

    object OnRemoveHeadMessageFromQueue: IngredientListEvents()
}