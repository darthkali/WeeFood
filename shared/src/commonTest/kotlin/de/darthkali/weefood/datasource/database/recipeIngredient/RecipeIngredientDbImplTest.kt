package de.darthkali.weefood.datasource.database.recipeIngredient

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.mockFactory.RecipeIngredientMock
import de.darthkali.weefood.testDbConnection
import de.darthkali.weefood.writeHead
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class RecipeIngredientDbImplTest : BaseTest() {

    private val weeFoodDatabase: WeeFoodDatabase = WeeFoodDatabase(testDbConnection())
    private val recipeIngredientDb: RecipeIngredientDb by lazy {
        RecipeIngredientDbImpl(
            weeFoodDatabase = weeFoodDatabase
        )
    }


    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        recipeIngredientDb.deleteAllRecipeIngredients()
        val recipeIngredients = RecipeIngredientMock.recipeIngredientList

        for (recipeIngredient in recipeIngredients) {
            recipeIngredientDb.insertRecipeIngredient(recipeIngredient)
        }
    }


    @Test
    fun get_all_recipe_ingredients_success() = runTest {
        writeHead("get_all_recipe_ingredients_success")
        val recipeIngredients = recipeIngredientDb.getAllRecipeIngredients()
        recipeIngredients.forEachIndexed { index, recipeIngredient ->
            println(recipeIngredient.toString())
            assertEquals(
                RecipeIngredientMock.recipeIngredientList[index].recipe_id,
                recipeIngredient.recipe_id
            )
        }
    }

    @Test
    fun get_all_recipe_ingredient_by_recipe_id_success() = runTest {
        writeHead("get_all_recipe_ingredient_by_recipe_id_success")

        RecipeIngredientMock.recipeIngredientList.forEachIndexed { index, recipeMock ->
            for (recipeIngredient in recipeIngredientDb.getAllRecipeIngredientByRecipeId(recipeMock.recipe_id)) {

                println(recipeIngredient.toString())
                assertEquals(
                    RecipeIngredientMock.recipeIngredientList[index].recipe_id,
                    recipeIngredient.recipe_id,
                )
            }
        }
    }


    @Test
    fun delete_all_recipe_ingredients_success() = runTest {
        writeHead("delete_all_recipe_ingredients_success")
        assertTrue(recipeIngredientDb.getAllRecipeIngredients().isNotEmpty())
        recipeIngredientDb.deleteAllRecipeIngredients()

        assertTrue(
            recipeIngredientDb.getAllRecipeIngredients().isEmpty(),
            "Delete All did not work"
        )
    }


    @Test
    fun delete_recipe_ingredient_by_id_success() = runTest {
        writeHead("delete_recipe_ingredient_by_id_success")

        recipeIngredientDb.getAllRecipeIngredients().forEachIndexed { index, recipeIngredient ->

            val recipeIngredientId = recipeIngredient.id
            println("Delete recipeIngredient with ID: $recipeIngredientId")
            recipeIngredientDb.deleteRecipeIngredientById(recipeIngredientId)

            assertEquals(
                expected = recipeIngredientDb.getAllRecipeIngredients().size,
                actual = RecipeIngredientMock.recipeIngredientList.size - (index + 1),
            )
        }
        assertEquals(
            expected = 0,
            actual = recipeIngredientDb.getAllRecipeIngredients().size,
        )
    }

    @Test
    fun insert_recipe_ingredient_success() = runTest {
        writeHead("insert_recipe_ingredient_success")

        for (recipeIngredient in recipeIngredientDb.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        recipeIngredientDb.insertRecipeIngredient(RecipeIngredientMock.recipeIngredient)

        for (recipeIngredient in recipeIngredientDb.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        assertEquals(
            expected = recipeIngredientDb.getAllRecipeIngredients().last(),
            actual = RecipeIngredientMock.recipeIngredient,
        )
    }
}