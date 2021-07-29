package de.darthkali.weefood.datasource.database.recipeIngredient

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.DatabaseHelper
import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.ingredient.IngredientDb
import de.darthkali.weefood.datasource.database.ingredient.IngredientDbImpl
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.testDbConnection
import de.darthkali.weefood.writeHead
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue


class RecipeIngredientDbImplTest : BaseTest() {

    private val weeFoodDatabase: WeeFoodDatabase = WeeFoodDatabase(testDbConnection())
    private val recipeIngredientDb: RecipeIngredientDb by lazy {
        RecipeIngredientDbImpl(
            weeFoodDatabase = weeFoodDatabase
        )
    }
//
//
//    @BeforeTest
//    fun setup() = runTest {
//        writeHead("setup")
//        ingredientDb.deleteAllIngredients()
//        val ingredients = IngredientMock.ingredientList
//
//        for (ingredient in ingredients) {
//            ingredientDb.insertIngredient(ingredient)
//        }
//    }
//
//
//    @Test
//    fun get_all_ingredients_success() = runTest {
//        writeHead("get_all_ingredients_success")
//        val ingredients = ingredientDb.getAllIngredients()
//        ingredients.forEachIndexed { index, ingredient ->
//            println(ingredient.toString())
//            assertEquals(
//                IngredientMock.ingredientList[index].name,
//                ingredient.name
//            )
//        }
//    }
//
//    @Test
//    fun get_ingredient_by_id_success() = runTest {
//        writeHead("get_ingredient_by_id_success")
//        IngredientMock.ingredientList.forEachIndexed { index, _ ->
//            val ingredient = ingredientDb.getIngredientById(index + 1)
//            println(ingredient.toString())
//            assertEquals(
//                IngredientMock.ingredientList[index].name,
//                ingredient?.name,
//            )
//        }
//    }
//
//
//    @Test
//    fun delete_all_ingredients_success() = runTest {
//        writeHead("delete_all_ingredients_success")
//        assertTrue(ingredientDb.getAllIngredients().isNotEmpty())
//        ingredientDb.deleteAllIngredients()
//
//        assertTrue(
//            ingredientDb.getAllIngredients().isEmpty(),
//            "Delete All did not work"
//        )
//    }
//
//
//    @Test
//    fun delete_ingredient_by_id_success() = runTest {
//        writeHead("delete_ingredient_by_id_success")
//
//        ingredientDb.getAllIngredients().forEachIndexed { index, ingredient ->
//
//            val ingredientId = ingredient.id
//            println("Delete Ingredient with ID: $ingredientId")
//            ingredientDb.deleteIngredientById(ingredientId)
//
//            assertEquals(
//                ingredientDb.getAllIngredients().size,
//                IngredientMock.ingredientList.size - (index + 1),
//            )
//
//            assertNull(
//                ingredientDb.getIngredientById(ingredientId)
//            )
//        }
//    }
//
//    @Test
//    fun insert_ingredient_success() = runTest {
//        writeHead("insert_ingredient_success")
//
//        for (ingredient in ingredientDb.getAllIngredients()) {
//            println(ingredient.toString())
//        }
//
//        ingredientDb.insertIngredient(IngredientMock.ingredient)
//
//        for (ingredient in ingredientDb.getAllIngredients()) {
//            println(ingredient.toString())
//        }
//
//
//        assertEquals(
//            ingredientDb.getAllIngredients().last().name,
//            IngredientMock.ingredient.name,
//        )
//
//        assertEquals(
//            ingredientDb.getAllIngredients().last().image,
//            IngredientMock.ingredient.image,
//        )
//    }


}