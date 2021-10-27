package de.darthkali.weefood.presentation.ingredient_list

/**
 * IngredientListState is a little different on iOS so it needs expect/actual
 * the ios State needs an extra attribute "bottomIngredient" to check the end of the list
 */
expect class IngredientListState

const val RECIPE_PAGINATION_PAGE_SIZE = 30
