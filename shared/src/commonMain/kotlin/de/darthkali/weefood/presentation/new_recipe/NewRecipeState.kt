package de.darthkali.weefood.presentation.new_recipe

import de.darthkali.weefood.domain.model.Recipe

data class NewRecipeState(

    val isLoading: Boolean = false,
    var changed: Int = 0,

    var recipe: Recipe = Recipe(
        databaseId = 0,
        name = " ",//TODO: leerzeichen, damit er das element im ViewModel kopiert? Häääää
        image = "",
        cooking_time = 0,
        cooking_time_unit = "",
        description = "",
        portion = 0,
        ingredients = listOf(),
    )
) {
    // Need secondary constructor to initialize with no args in SwiftUI

    constructor() : this(
        isLoading = false,

        recipe = Recipe(
            databaseId = 0,
            name = " ",
            image = "",
            cooking_time = 0,
            cooking_time_unit = "",
            description = "",
            portion = 0,
            ingredients = listOf()
        )
    )
}