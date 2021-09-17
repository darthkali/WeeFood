package de.darthkali.weefood.datasource.database.repository.ingredient

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

    private val ingredientRepository: IngredientRepository by inject()

    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        ingredientRepository.deleteAllIngredients()
        val ingredients = IngredientMock.ingredientDbList

        for (ingredient in ingredients) {
            ingredientRepository.insertIngredient(ingredient)
        }
    }

    @Test
    fun get_all_ingredients_success() = runTest {
        writeHead("get_all_ingredients_success")
        val ingredients = ingredientRepository.getAllIngredients()
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
        val ingredients = ingredientRepository.getAllIngredients()

        for (ingredientItem in ingredients) {
            val ingredient = ingredientRepository.getIngredientById(ingredientItem.id)
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
        assertTrue(ingredientRepository.getAllIngredients().isNotEmpty())
        ingredientRepository.deleteAllIngredients()

        assertTrue(
            ingredientRepository.getAllIngredients().isEmpty(),
            "Delete All did not work"
        )
    }

    @Test
    fun delete_ingredient_by_id_success() = runTest {
        writeHead("delete_ingredient_by_id_success")

        ingredientRepository.getAllIngredients().forEachIndexed { index, ingredient ->

            val ingredientId = ingredient.id
            println("Delete Ingredient with ID: $ingredientId")
            ingredientRepository.deleteIngredientById(ingredientId)

            assertEquals(
                ingredientRepository.getAllIngredients().size,
                IngredientMock.ingredientDbList.size - (index + 1),
            )
            assertNull(
                ingredientRepository.getIngredientById(ingredientId)
            )
        }
    }

    @Test
    fun insert_ingredient_success() = runTest {
        writeHead("insert_ingredient_success")

        for (ingredient in ingredientRepository.getAllIngredients()) {
            println(ingredient.toString())
        }

        ingredientRepository.insertIngredient(IngredientMock.ingredientDb)

        for (ingredient in ingredientRepository.getAllIngredients()) {
            println(ingredient.toString())
        }
        assertEquals(ingredientRepository.getAllIngredients().last(), IngredientMock.ingredientDb)
    }

    @Test
    fun update_recipe_success() = runTest {
        writeHead("update_recipe_success")

        for (recipe in ingredientRepository.getAllIngredients()) {
            println(recipe.toString())
        }

        ingredientRepository.updateIngredientByApiId(IngredientMock.ingredientDbUpdate)

        for (recipe in ingredientRepository.getAllIngredients()) {
            println(recipe.toString())
        }
        assertEquals(
            expected = IngredientMock.ingredientDbUpdate,
            actual = ingredientRepository.getAllIngredients()[IngredientMock.ingredientDbUpdateIndex],
        )
    }
}