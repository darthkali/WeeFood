package de.darthkali.weefood.datasource.interactors.recipe_ingredient

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.repository.ingredient.IngredientRepository
import de.darthkali.weefood.datasource.database.repository.recipe.RecipeRepository
import de.darthkali.weefood.datasource.database.repository.recipeIngredient.RecipeIngredientRepository
import de.darthkali.weefood.interactors.recipe_ingredient.GetIngredientsFromRecipe
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.mockFactory.RecipeIngredientMock
import de.darthkali.weefood.mockFactory.RecipeMock
import de.darthkali.weefood.writeHead
import org.koin.core.component.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetIngredientsFromRecipeTest : BaseTest() {

    private val ingredientRepository: IngredientRepository by inject()
    private val recipeRepository: RecipeRepository by inject()
    private val recipeIngredientRepository: RecipeIngredientRepository by inject()
    private val getIngredientsFromRecipe: GetIngredientsFromRecipe by inject()

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
    fun get_recipe_ingredients_success() = runTest {
        writeHead("get_recipe_ingredients_success")

        val ingredients = getIngredientsFromRecipe.execute(recipeRepository.getAllRecipes().first().id)

        println("All RecipeIngredients:-----------------------------------------")
        for (recipeIngredient in recipeIngredientRepository.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        println("Found Ingredients with RecipeID:-------------------------------")
        if (ingredients != null) {
            for (ingredient in ingredients) {
                println(ingredient.toString())
            }
        }

        println("AssertEqual:---------------------------------------------------")
        ingredients?.forEachIndexed { index, ingredient ->
            println(ingredient.toString())
            println("expected ${RecipeIngredientMock.recipeIngredientDbList[index]}")
            println("actual $ingredient")

            assertEquals(
                expected = RecipeIngredientMock.recipeIngredientDbList[index].ingredient_id,
                actual = ingredient.internalId,
            )
        }
    }
}
