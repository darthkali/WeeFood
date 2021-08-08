package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.datasource.database.model.IngredientDb
import de.darthkali.weefood.domain.model.Ingredient

object IngredientMock {

    val ingredientDb = IngredientDb(name = "Butter", image = "butter-sliced.jpg", apiId = 1001)
    val ingredient = Ingredient(
        name = "Butter",
        image = "butter-sliced.jpg",
        apiId = 1001,
        quantity = 0F,
        unit = ""
    )

    val ingredientDbList = listOf(
        IngredientDb(name = "Banane", image = "bananas.jpg", apiId = 9040),
        IngredientDb(name = "Apfel", image = "apple.jpg", apiId = 9003),
        IngredientDb(name = "Mehl", image = "flour.png", apiId = 20081),
        IngredientDb(name = "Zucker", image = "sugar-in-bowl.png", apiId = 19335),
        IngredientDb(name = "Nudeln", image = "fusilli.jpg", apiId = 20420),
        IngredientDb(name = "Reis", image = "uncooked-white-rice.png", apiId = 20444),
        IngredientDb(name = "Milch", image = "milk.png", apiId = 1077),
        IngredientDb(name = "Tee", image = "tea-bags.jpg", apiId = 14355),
        IngredientDb(name = "Kaffee", image = "brewed-coffee.jpg", apiId = 14209),
        IngredientDb(name = "Eier", image = "egg.png", apiId = 1123),
    )

    val ingredientList = listOf(
        Ingredient(
            name = "Banane",
            image = "bananas.jpg",
            apiId = 9040,
            quantity = 5F,
            unit = "Hase"
        ),
        Ingredient(
            name = "Apfel",
            image = "apple.jpg",
            apiId = 9003, quantity = 6F,
            unit = "Hase"
        ),
        Ingredient(
            name = "Mehl",
            image = "flour.png",
            apiId = 20081,
            quantity = 7F,
            unit = "Hase"
        ),
        Ingredient(
            name = "Zucker",
            image = "sugar-in-bowl.png",
            apiId = 19335,
            quantity = 8F,
            unit = "Hase"
        ),
        Ingredient(
            name = "Nudeln",
            image = "fusilli.jpg",
            apiId = 20420,
            quantity = 9F,
            unit = "Hase"
        ),
        Ingredient(
            name = "Reis",
            image = "uncooked-white-rice.png",
            apiId = 20444,
            quantity = 5F,
            unit = "Hase"
        ),
        Ingredient(name = "Milch", image = "milk.png", apiId = 1077, quantity = 5F, unit = "Hase"),
        Ingredient(
            name = "Tee",
            image = "tea-bags.jpg",
            apiId = 14355,
            quantity = 5F,
            unit = "Hase"
        ),
        Ingredient(
            name = "Kaffee",
            image = "brewed-coffee.jpg",
            apiId = 14209,
            quantity = 5F,
            unit = "Hase"
        ),
        Ingredient(name = "Eier", image = "egg.png", apiId = 1123, quantity = 5F, unit = "Hase"),
    )

    const val ingredientDbUpdateIndex = 2
    val ingredientDbUpdate = IngredientDb(
        id = ingredientDbUpdateIndex + 1,
        name = "${IngredientMock.ingredientDbList[ingredientDbUpdateIndex].name} update",
        image = "${IngredientMock.ingredientDbList[ingredientDbUpdateIndex].image} update",
        apiId = IngredientMock.ingredientDbList[ingredientDbUpdateIndex].apiId,
    )
}