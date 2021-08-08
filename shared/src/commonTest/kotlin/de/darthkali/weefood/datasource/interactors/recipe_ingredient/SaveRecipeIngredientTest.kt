package de.darthkali.weefood.datasource.interactors.recipe_ingredient

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueries
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.interactors.recipe_ingredient.SaveRecipeIngredient
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.mockFactory.RecipeIngredientMock
import de.darthkali.weefood.mockFactory.RecipeMock
import de.darthkali.weefood.writeHead
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.koin.core.component.inject

class SaveRecipeIngredientTest : BaseTest() {

    private val ingredientQueries: IngredientQueries by inject()
    private val recipeQueries: RecipeQueries by inject()
    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val saveRecipeIngredient: SaveRecipeIngredient by inject()

    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        ingredientQueries.deleteAllIngredients()
        recipeQueries.deleteAllRecipes()
        recipeIngredientQueries.deleteAllRecipeIngredients()

        val ingredients = IngredientMock.ingredientDbList
        val recipes = RecipeMock.recipeListDb
        val recipeIngredients = RecipeIngredientMock.recipeIngredientDbList


        for (ingredient in ingredients) {
            ingredientQueries.insertIngredient(ingredient)
        }

        for (recipe in recipes) {
            recipeQueries.insertRecipe(recipe)
        }

        for (recipeIngredient in recipeIngredients) {
            recipeIngredientQueries.insertRecipeIngredient(recipeIngredient)
        }
    }


    /**
     * should save a new recipeIngredient to the database
     * because the recipeId and the ingredientId are not the same
     */
    @Test
    fun save_recipe_ingredient_success() = runTest {
        writeHead("insert_recipe_ingredient_success")

        for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        saveRecipeIngredient.execute(RecipeIngredientMock.recipeIngredientDb)

        for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        assertEquals(
            expected = RecipeIngredientMock.recipeIngredientDb ,
            actual = recipeIngredientQueries.getAllRecipeIngredients().last(),
        )
    }


    /**
     * should update the recipeIngredient in the database
     * because the recipeId and the ingredientId are the same
     */
    @Test
    fun update_recipe_ingredient_success() = runTest {
        writeHead("update_recipe_ingredient_success")

        for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        saveRecipeIngredient.execute(RecipeIngredientMock.recipeIngredientDbUpdate)

        for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        assertEquals(
            expected = RecipeIngredientMock.recipeIngredientDbUpdate ,
            actual = recipeIngredientQueries.getAllRecipeIngredients()[RecipeIngredientMock.recipeIngredientDbUpdateIndex],
        )
    }

}