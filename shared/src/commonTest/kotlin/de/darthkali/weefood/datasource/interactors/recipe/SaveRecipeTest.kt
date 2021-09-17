package de.darthkali.weefood.datasource.interactors.recipe

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.repository.ingredient.IngredientRepository
import de.darthkali.weefood.datasource.database.repository.recipe.RecipeRepository
import de.darthkali.weefood.datasource.database.repository.recipeIngredient.RecipeIngredientRepository
import de.darthkali.weefood.interactors.recipe.GetRecipe
import de.darthkali.weefood.interactors.recipe.SaveRecipe
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.mockFactory.RecipeIngredientMock
import de.darthkali.weefood.mockFactory.RecipeMock
import de.darthkali.weefood.writeHead
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.koin.core.component.inject

class SaveRecipeTest : BaseTest() {

    private val ingredientRepository: IngredientRepository by inject()
    private val recipeRepository: RecipeRepository by inject()
    private val recipeIngredientRepository: RecipeIngredientRepository by inject()
    private val saveRecipe: SaveRecipe by inject()
    private val getRecipe: GetRecipe by inject()

    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        ingredientRepository.deleteAllIngredients()
        recipeRepository.deleteAllRecipes()
        recipeIngredientRepository.deleteAllRecipeIngredients()

        val ingredients = IngredientMock.ingredientDbList
        val recipes = RecipeMock.recipeListDb
        val recipeIngredients = RecipeIngredientMock.recipeIngredientDbList

        for (ingredient in ingredients) {
            ingredientRepository.insertIngredient(ingredient)
        }

        for (recipe in recipes) {
            recipeRepository.insertRecipe(recipe)
        }

        for (recipeIngredient in recipeIngredients) {
            recipeIngredientRepository.insertRecipeIngredient(recipeIngredient)
        }
    }


    /**
     * should save a new recipeIngredient to the database
     * because the recipeId and the ingredientId are not the same
     */
    @Test
    fun save_recipe_success() = runTest {
        writeHead("save_recipe_success")

        for (recipe in recipeRepository.getAllRecipes()) {
            println(recipe.toString())
        }

        saveRecipe.execute(RecipeMock.recipe)

        for (recipeIngredient in recipeIngredientRepository.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        assertEquals(
            expected = RecipeMock.recipe,
            actual = getRecipe.execute(recipeRepository.getAllRecipes().last().id),
        )
    }


    /**
     * should update the recipeIngredient in the database
     * because the recipeId and the ingredientId are the same
     */
    @Test
    fun update_recipe_success() = runTest {
        writeHead("update_recipe_success")

        for (recipe in recipeRepository.getAllRecipes()) {
            println(recipe.toString())
        }

        saveRecipe.execute(RecipeMock.recipeUpdate)

        for (recipeIngredient in recipeIngredientRepository.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        assertEquals(
            expected = RecipeMock.recipeUpdate,
            actual = getRecipe.execute(RecipeMock.recipeUpdate.databaseId!!),
        )
    }
}