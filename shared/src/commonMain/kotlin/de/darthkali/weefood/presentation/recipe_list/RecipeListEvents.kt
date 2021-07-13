package de.darthkali.weefood.presentation.recipe_list

sealed class RecipeListEvents {

    object LoadRecipes : RecipeListEvents()

    object NextPage : RecipeListEvents()

    object NewSearch : RecipeListEvents()

    data class OnUpdateQuery(val query: String): RecipeListEvents()

    data class OnSelectCategory(val category: FoodCategory): RecipeListEvents()
}