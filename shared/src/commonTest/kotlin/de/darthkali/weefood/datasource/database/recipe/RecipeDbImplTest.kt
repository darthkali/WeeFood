package de.darthkali.weefood.datasource.database.recipe

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.mockFactory.RecipeMock
import de.darthkali.weefood.testDbConnection
import de.darthkali.weefood.writeHead
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RecipeDbImplTest : BaseTest() {

    private val weeFoodDatabase: WeeFoodDatabase = WeeFoodDatabase(testDbConnection())
    private val recipeDb: RecipeDb by lazy {
        RecipeDbImpl(
            weeFoodDatabase = weeFoodDatabase
        )
    }


    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        recipeDb.deleteAllRecipes()
        val recipes = RecipeMock.recipeList

        for (recipe in recipes) {
            recipeDb.insertRecipe(recipe)
        }
    }


    @Test
    fun get_all_recipes_success() = runTest {
        writeHead("get_all_recipes_success")
        val recipes = recipeDb.getAllRecipes()
        recipes.forEachIndexed { index, recipe ->
            println(recipe.toString())
            assertEquals(
                expected = RecipeMock.recipeList[index].name,
                actual = recipe.name
            )
        }
    }

    @Test
    fun get_recipe_by_id_success() = runTest {
        writeHead("get_recipe_by_id_success")

        val recipes = recipeDb.getAllRecipes()

        for (recipeItem in recipes) {
            val recipe = recipeDb.getRecipeById(recipeItem.id)
            println(recipe.toString())
            assertEquals(
                expected = recipeItem.id,
                actual = recipe?.id,
            )
        }
    }

    @Test
    fun search_recipes_success() = runTest {
        writeHead("search_recipes_success")

        recipeDb.deleteAllRecipes()
        val recipes = RecipeMock.recipeListForSearchByName

        for (recipe in recipes) {
            recipeDb.insertRecipe(recipe)
        }

        for (recipe in recipeDb.searchRecipes(RecipeMock.searchName)) {
            println(recipe.toString())
            assertEquals(
                expected = "true",
                actual = recipe.image,
            )
        }
    }


    @Test
    fun delete_all_recipes_success() = runTest {
        writeHead("delete_all_recipes_success")
        assertTrue(recipeDb.getAllRecipes().isNotEmpty())
        recipeDb.deleteAllRecipes()

        assertTrue(
            recipeDb.getAllRecipes().isEmpty(),
            "Delete All did not work"
        )
    }


    @Test
    fun delete_recipe_by_id_success() = runTest {
        writeHead("delete_recipe_by_id_success")

        recipeDb.getAllRecipes().forEachIndexed { index, recipe ->

            val recipeId = recipe.id
            println("Delete recipe with ID: $recipeId")
            recipeDb.deleteRecipeById(recipeId)

            assertEquals(
                expected = recipeDb.getAllRecipes().size,
                actual = RecipeMock.recipeList.size - (index + 1),
            )

            assertNull(
                recipeDb.getRecipeById(recipeId)
            )
        }
    }

    @Test
    fun insert_recipe_success() = runTest {
        writeHead("insert_recipe_success")

        for (recipe in recipeDb.getAllRecipes()) {
            println(recipe.toString())
        }

        recipeDb.insertRecipe(RecipeMock.recipe)

        for (recipe in recipeDb.getAllRecipes()) {
            println(recipe.toString())
        }


        assertEquals(
            expected = recipeDb.getAllRecipes().last(),
            actual = RecipeMock.recipe,
        )
    }

}