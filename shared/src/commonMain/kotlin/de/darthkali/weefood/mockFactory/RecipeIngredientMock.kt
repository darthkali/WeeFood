package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.model.RecipeIngredient

object RecipeIngredientMock {

    val sizeRecipes = RecipeMock.recipeList.size
    val sizeIngredients = IngredientMock.ingredientList.size
    // TODO: Die Liste ist abänhing von den Rezepten und den Zutaten. Muss man ggf noch was anpassen


    val recipeIngredient = RecipeIngredient(quantity = 200.0, unit = "gramm", recipe_id = 1, ingredient_id = 1)

    val recipeIngredientList = listOf(
        RecipeIngredient(quantity = 200.0, unit = "gramm", recipe_id = 1, ingredient_id = 1),
        RecipeIngredient(quantity = 1.0, unit = "Stück", recipe_id = 1, ingredient_id = 2),
        RecipeIngredient(quantity = 0.5, unit = "Stück", recipe_id = 2, ingredient_id = 3),
        RecipeIngredient(quantity = 100.0, unit = "gramm", recipe_id = 2, ingredient_id = 4),
        RecipeIngredient(quantity = 20.0, unit = "gramm", recipe_id = 3, ingredient_id = 5),
        RecipeIngredient(quantity = 1.0, unit = "TL", recipe_id = 3, ingredient_id = 6),
        RecipeIngredient(quantity = 1.0, unit = "Stück", recipe_id = 4, ingredient_id = 7),
        RecipeIngredient(quantity = 50.0, unit = "gramm", recipe_id = 4, ingredient_id = 8)
    )
}