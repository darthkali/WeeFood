package de.darthkali.weefood.interactors.recipe_list

import de.darthkali.weefood.presentation.recipe_list.FoodCategory

sealed class RecipeListEvents {

    object LoadRecipes : RecipeListEvents()

    object NextPage : RecipeListEvents()

    object NewSearch : RecipeListEvents()

    data class OnUpdateQuery(val query: String): RecipeListEvents()

    data class OnSelectedCategory(val category: FoodCategory): RecipeListEvents()
}