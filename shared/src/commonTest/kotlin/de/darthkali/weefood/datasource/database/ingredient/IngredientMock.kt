package de.darthkali.weefood.datasource.database.ingredient

import de.darthkali.weefood.domain.model.Ingredient

object IngredientMock {

    val ingredient = Ingredient(id = 1, name = "name1", "no.jpg")

    val ingredientList = listOf<Ingredient>(
        Ingredient(id = 1, name = "name1", "no.jpg"),
        Ingredient(id = 2, name = "name2", "no.jpg"),
        Ingredient(id = 3, name = "name3", "no.jpg")
    )
}