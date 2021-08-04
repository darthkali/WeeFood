package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.datasource.database.model.IngredientDb

object IngredientMock {

    val ingredient = IngredientDb(name = "Butter", image = "butter-sliced.jpg", apiId = 1001)

    val ingredientList = listOf(
        IngredientDb(name = "Banane", image = "bananas.jpg", apiId = 9040),
        IngredientDb(name = "Apfel", image = "apple.jpg", apiId = 9003),
        IngredientDb(name = "Mehl", image = "flour.png", apiId = 20081),
        IngredientDb(name = "Zucker", image = "sugar-in-bowl.png", apiId = 19335),
        IngredientDb(name = "Nudeln", image = "fusilli.jpg", apiId = 20420),
        IngredientDb(name = "Reis", image = "uncooked-white-rice.png", apiId =20444 ),
        IngredientDb(name = "Milch", image = "milk.png", apiId = 1077),
        IngredientDb(name = "Tee", image = "tea-bags.jpg", apiId = 14355),
        IngredientDb(name = "Kaffee", image = "brewed-coffee.jpg", apiId = 14209),
        IngredientDb(name = "Eier", image = "egg.png", apiId = 1123),
    )
}