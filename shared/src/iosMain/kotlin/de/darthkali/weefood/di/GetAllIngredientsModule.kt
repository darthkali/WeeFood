package de.darthkali.weefood.di

import de.darthkali.weefood.interactors.recipe_list.GetAllIngredients

class GetAllIngredientsModule(
    val databaseModule: DatabaseModule,
) {
    val getAllIngredient: GetAllIngredients by lazy {
        GetAllIngredients(
            ingredientDb = databaseModule.ingredientDb
        )
    }
}

