package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.domain.model.Ingredient

object IngredientMock {

    val ingredient = Ingredient(name = "Butter", image = "butter-sliced.jpg")

    val ingredientList = listOf(
        Ingredient(name = "Banane", image = "bananas.jpg"),
        Ingredient(name = "Apfel", image = "apple.jpg"),
        Ingredient(name = "Mehl", image = "flour.png"),
        Ingredient(name = "Zucker", image = "sugar-in-bowl.png"),
        Ingredient(name = "Nudeln", image = "fusilli.jpg"),
        Ingredient(name = "Reis", image = "uncooked-white-rice.png"),
        Ingredient(name = "Milch", image = "milk.png"),
        Ingredient(name = "Tee", image = "tea-bags.jpg"),
        Ingredient(name = "Kaffee", image = "brewed-coffee.jpg"),
        Ingredient(name = "Eier", image = "egg.png"),
    )
}