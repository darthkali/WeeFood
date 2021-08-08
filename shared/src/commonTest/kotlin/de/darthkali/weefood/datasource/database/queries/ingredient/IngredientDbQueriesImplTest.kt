package de.darthkali.weefood.datasource.database.queries.ingredient

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.writeHead
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.koin.core.component.inject

class IngredientDbQueriesImplTest : BaseTest() {

    private val ingredientQueries: IngredientQueries by inject()

    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        ingredientQueries.deleteAllIngredients()
        val ingredients = IngredientMock.ingredientDbList

        for (ingredient in ingredients) {
            ingredientQueries.insertIngredient(ingredient)
        }
    }

    @Test
    fun get_all_ingredients_success() = runTest {
        writeHead("get_all_ingredients_success")
        val ingredients = ingredientQueries.getAllIngredients()
        ingredients.forEachIndexed { index, ingredient ->
            println(ingredient.toString())
            assertEquals(
                IngredientMock.ingredientDbList[index].name,
                ingredient.name
            )
        }
    }

    @Test
    fun get_ingredient_by_id_success() = runTest {
        writeHead("get_ingredient_by_id_success")
        val ingredients = ingredientQueries.getAllIngredients()

        for (ingredientItem in ingredients) {
            val ingredient = ingredientQueries.getIngredientById(ingredientItem.id)
            println(ingredient.toString())
            assertEquals(
                expected = ingredientItem.id,
                actual = ingredient?.id,
            )
        }
    }

    @Test
    fun delete_all_ingredients_success() = runTest {
        writeHead("delete_all_ingredients_success")
        assertTrue(ingredientQueries.getAllIngredients().isNotEmpty())
        ingredientQueries.deleteAllIngredients()

        assertTrue(
            ingredientQueries.getAllIngredients().isEmpty(),
            "Delete All did not work"
        )
    }

    @Test
    fun delete_ingredient_by_id_success() = runTest {
        writeHead("delete_ingredient_by_id_success")

        ingredientQueries.getAllIngredients().forEachIndexed { index, ingredient ->

            val ingredientId = ingredient.id
            println("Delete Ingredient with ID: $ingredientId")
            ingredientQueries.deleteIngredientById(ingredientId)

            assertEquals(
                ingredientQueries.getAllIngredients().size,
                IngredientMock.ingredientDbList.size - (index + 1),
            )
            assertNull(
                ingredientQueries.getIngredientById(ingredientId)
            )
        }
    }

    @Test
    fun insert_ingredient_success() = runTest {
        writeHead("insert_ingredient_success")

        for (ingredient in ingredientQueries.getAllIngredients()) {
            println(ingredient.toString())
        }

        ingredientQueries.insertIngredient(IngredientMock.ingredientDb)

        for (ingredient in ingredientQueries.getAllIngredients()) {
            println(ingredient.toString())
        }
        assertEquals(ingredientQueries.getAllIngredients().last(), IngredientMock.ingredientDb)
    }

    @Test
    fun update_recipe_success() = runTest {
        writeHead("update_recipe_success")

        for (recipe in ingredientQueries.getAllIngredients()) {
            println(recipe.toString())
        }

        ingredientQueries.updateIngredientByApiId(IngredientMock.ingredientDbUpdate)

        for (recipe in ingredientQueries.getAllIngredients()) {
            println(recipe.toString())
        }
        assertEquals(
            expected = IngredientMock.ingredientDbUpdate,
            actual = ingredientQueries.getAllIngredients()[IngredientMock.ingredientDbUpdateIndex],
        )
    }
}