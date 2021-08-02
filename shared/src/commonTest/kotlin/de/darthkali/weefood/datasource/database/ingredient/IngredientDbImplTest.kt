package de.darthkali.weefood.datasource.database.ingredient

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.di.*
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.writeHead
import kotlin.test.*
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class IngredientDbImplTest : BaseTest() {

    private val ingredientDb: IngredientDb by inject()

    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        ingredientDb.deleteAllIngredients()
        val ingredients = IngredientMock.ingredientList

        for (ingredient in ingredients) {
            ingredientDb.insertIngredient(ingredient)
        }
    }

    @Test
    fun get_all_ingredients_success() = runTest {
        writeHead("get_all_ingredients_success")
        val ingredients = ingredientDb.getAllIngredients()
        ingredients.forEachIndexed { index, ingredient ->
            println(ingredient.toString())
            assertEquals(
                IngredientMock.ingredientList[index].name,
                ingredient.name
            )
        }
    }

    @Test
    fun get_ingredient_by_id_success() = runTest {
        writeHead("get_ingredient_by_id_success")
        val ingredients = ingredientDb.getAllIngredients()

        for(ingredientItem in ingredients!!) {
            val ingredient = ingredientDb.getIngredientById(ingredientItem.id)
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
        assertTrue(ingredientDb.getAllIngredients().isNotEmpty())
        ingredientDb.deleteAllIngredients()

        assertTrue(
            ingredientDb.getAllIngredients().isEmpty(),
            "Delete All did not work"
        )
    }

    @Test
    fun delete_ingredient_by_id_success() = runTest {
        writeHead("delete_ingredient_by_id_success")

        ingredientDb.getAllIngredients().forEachIndexed { index, ingredient ->

            val ingredientId = ingredient.id
            println("Delete Ingredient with ID: $ingredientId")
            ingredientDb.deleteIngredientById(ingredientId)

            assertEquals(
                ingredientDb.getAllIngredients().size,
                IngredientMock.ingredientList.size - (index + 1),
            )

            assertNull(
                ingredientDb.getIngredientById(ingredientId)
            )
        }
    }

    @Test
    fun insert_ingredient_success() = runTest {
        writeHead("insert_ingredient_success")

        for (ingredient in ingredientDb.getAllIngredients()) {
            println(ingredient.toString())
        }

        ingredientDb.insertIngredient(IngredientMock.ingredient)

        for (ingredient in ingredientDb.getAllIngredients()) {
            println(ingredient.toString())
        }

        assertEquals(ingredientDb.getAllIngredients().last(), IngredientMock.ingredient)
    }

}