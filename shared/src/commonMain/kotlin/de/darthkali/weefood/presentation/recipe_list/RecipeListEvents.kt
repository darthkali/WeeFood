package de.darthkali.weefood.presentation.recipe_list


sealed class RecipeListEvents {
    object LoadRecipe: RecipeListEvents()
    object NewSearch: RecipeListEvents()
    object NextPage: RecipeListEvents()
    data class OnUpdateQuery(val query: String): RecipeListEvents()
}

