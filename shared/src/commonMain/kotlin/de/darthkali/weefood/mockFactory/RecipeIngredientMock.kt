package de.darthkali.weefood.mockFactory

import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb

object RecipeIngredientMock {

    val sizeRecipes = RecipeMock.recipeListDb.size
    val sizeIngredients = IngredientMock.ingredientDbList.size
    // TODO: Die Liste ist abänhing von den Rezepten und den Zutaten. Muss man ggf noch was anpassen


    val recipeIngredientDb = RecipeIngredientDb(quantity = 2F, unit = "gramm", recipe_id = 1, ingredient_id = 1)

    val recipeIngredientDbList = listOf(
        RecipeIngredientDb(quantity = 200F, unit = "gramm", recipe_id = 1, ingredient_id = 1),
        RecipeIngredientDb(quantity = 1F, unit = "Stück", recipe_id = 1, ingredient_id = 2),
        RecipeIngredientDb(quantity = 0.5F, unit = "Stück", recipe_id = 2, ingredient_id = 3),
        RecipeIngredientDb(quantity = 100F, unit = "gramm", recipe_id = 2, ingredient_id = 4),
        RecipeIngredientDb(quantity = 20F, unit = "gramm", recipe_id = 3, ingredient_id = 5),
        RecipeIngredientDb(quantity = 1F, unit = "TL", recipe_id = 3, ingredient_id = 6),
        RecipeIngredientDb(quantity = 1F, unit = "Stück", recipe_id = 4, ingredient_id = 7),
        RecipeIngredientDb(quantity = 50F, unit = "gramm", recipe_id = 4, ingredient_id = 8)
    )
}