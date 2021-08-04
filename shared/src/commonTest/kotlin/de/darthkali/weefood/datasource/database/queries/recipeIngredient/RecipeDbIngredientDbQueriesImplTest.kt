package de.darthkali.weefood.datasource.database.recipeIngredient

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.mockFactory.RecipeIngredientMock
import de.darthkali.weefood.writeHead
import org.koin.core.component.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class RecipeDbIngredientDbQueriesImplTest : BaseTest() {

    private val recipeIngredientQueries: RecipeIngredientQueries by inject()


    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        recipeIngredientQueries.deleteAllRecipeIngredients()
        val recipeIngredients = RecipeIngredientMock.recipeIngredientList

        for (recipeIngredient in recipeIngredients) {
            recipeIngredientQueries.insertRecipeIngredient(recipeIngredient)
        }
    }


    @Test
    fun get_all_recipe_ingredients_success() = runTest {
        writeHead("get_all_recipe_ingredients_success")
        val recipeIngredients = recipeIngredientQueries.getAllRecipeIngredients()
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
            for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredientByRecipeId(recipeMock.recipe_id)) {

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
        assertTrue(recipeIngredientQueries.getAllRecipeIngredients().isNotEmpty())
        recipeIngredientQueries.deleteAllRecipeIngredients()

        assertTrue(
            recipeIngredientQueries.getAllRecipeIngredients().isEmpty(),
            "Delete All did not work"
        )
    }


    @Test
    fun delete_recipe_ingredient_by_id_success() = runTest {
        writeHead("delete_recipe_ingredient_by_id_success")

        recipeIngredientQueries.getAllRecipeIngredients().forEachIndexed { index, recipeIngredient ->

            val recipeIngredientId = recipeIngredient.id
            println("Delete recipeIngredient with ID: $recipeIngredientId")
            recipeIngredientQueries.deleteRecipeIngredientById(recipeIngredientId)

            assertEquals(
                expected = recipeIngredientQueries.getAllRecipeIngredients().size,
                actual = RecipeIngredientMock.recipeIngredientList.size - (index + 1),
            )
        }
        assertEquals(
            expected = 0,
            actual = recipeIngredientQueries.getAllRecipeIngredients().size,
        )
    }

    @Test
    fun insert_recipe_ingredient_success() = runTest {
        writeHead("insert_recipe_ingredient_success")

        for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        recipeIngredientQueries.insertRecipeIngredient(RecipeIngredientMock.recipeIngredient)

        for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        assertEquals(
            expected = recipeIngredientQueries.getAllRecipeIngredients().last(),
            actual = RecipeIngredientMock.recipeIngredient,
        )
    }
}