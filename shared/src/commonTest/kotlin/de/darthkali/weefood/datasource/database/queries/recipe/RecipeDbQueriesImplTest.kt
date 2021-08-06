package de.darthkali.weefood.datasource.database.queries.recipe

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.mockFactory.RecipeMock
import de.darthkali.weefood.writeHead
import org.koin.core.component.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RecipeDbQueriesImplTest : BaseTest() {

    private val recipeQueries: RecipeQueries by inject()


    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        recipeQueries.deleteAllRecipes()
        val recipes = RecipeMock.recipeListDb

        for (recipe in recipes) {
            recipeQueries.insertRecipe(recipe)
        }
    }


    @Test
    fun get_all_recipes_success() = runTest {
        writeHead("get_all_recipes_success")
        val recipes = recipeQueries.getAllRecipes()
        recipes.forEachIndexed { index, recipe ->
            println(recipe.toString())
            assertEquals(
                expected = RecipeMock.recipeListDb[index].name,
                actual = recipe.name
            )
        }
    }

    @Test
    fun get_recipe_by_id_success() = runTest {
        writeHead("get_recipe_by_id_success")

        val recipes = recipeQueries.getAllRecipes()

        for (recipeItem in recipes) {
            val recipe = recipeQueries.getRecipeById(recipeItem.id)
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

        recipeQueries.deleteAllRecipes()
        val recipes = RecipeMock.recipeListForSearchByName

        for (recipe in recipes) {
            recipeQueries.insertRecipe(recipe)
        }

        for (recipe in recipeQueries.searchRecipes(RecipeMock.searchName)) {
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
        assertTrue(recipeQueries.getAllRecipes().isNotEmpty())
        recipeQueries.deleteAllRecipes()

        assertTrue(
            recipeQueries.getAllRecipes().isEmpty(),
            "Delete All did not work"
        )
    }


    @Test
    fun delete_recipe_by_id_success() = runTest {
        writeHead("delete_recipe_by_id_success")

        recipeQueries.getAllRecipes().forEachIndexed { index, recipe ->

            val recipeId = recipe.id
            println("Delete recipe with ID: $recipeId")
            recipeQueries.deleteRecipeById(recipeId)

            assertEquals(
                expected = recipeQueries.getAllRecipes().size,
                actual = RecipeMock.recipeListDb.size - (index + 1),
            )

            assertNull(
                recipeQueries.getRecipeById(recipeId)
            )
        }
    }

    @Test
    fun insert_recipe_success() = runTest {
        writeHead("insert_recipe_success")

        for (recipe in recipeQueries.getAllRecipes()) {
            println(recipe.toString())
        }

        recipeQueries.insertRecipe(RecipeMock.recipeDb)

        for (recipe in recipeQueries.getAllRecipes()) {
            println(recipe.toString())
        }


        assertEquals(
            expected = recipeQueries.getAllRecipes().last(),
            actual = RecipeMock.recipeDb,
        )
    }

    @Test
    fun update_recipe_success() = runTest {
        writeHead("update_recipe_success")

        for (recipe in recipeQueries.getAllRecipes()) {
            println(recipe.toString())
        }

        recipeQueries.updateRecipe(RecipeMock.recipeDbUpdate)

        for (recipe in recipeQueries.getAllRecipes()) {
            println(recipe.toString())
        }


        assertEquals(
            expected = recipeQueries.getAllRecipes()[RecipeMock.recipeDbUpdateIndex],
            actual = RecipeMock.recipeDbUpdate,
        )
    }

}