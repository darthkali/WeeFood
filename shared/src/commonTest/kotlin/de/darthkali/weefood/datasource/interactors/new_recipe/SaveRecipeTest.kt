package de.darthkali.weefood.datasource.interactors.new_recipe

import de.darthkali.weefood.BaseTest
import de.darthkali.weefood.datasource.database.mapper.ingredient.IngredientMapper
import de.darthkali.weefood.datasource.database.mapper.recipe.RecipeMapper
import de.darthkali.weefood.datasource.database.queries.ingredient.IngredientQueries
import de.darthkali.weefood.datasource.database.queries.recipe.RecipeQueries
import de.darthkali.weefood.datasource.database.queries.recipeIngredient.RecipeIngredientQueries
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.interactors.new_recipe.GetRecipe
import de.darthkali.weefood.interactors.new_recipe.SaveRecipe
import de.darthkali.weefood.interactors.new_recipe.SaveRecipeIngredient
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.mockFactory.RecipeIngredientMock
import de.darthkali.weefood.mockFactory.RecipeMock
import de.darthkali.weefood.writeHead
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.koin.core.component.inject

class SaveRecipeTest : BaseTest() {

    private val ingredientQueries: IngredientQueries by inject()
    private val recipeQueries: RecipeQueries by inject()
    private val recipeIngredientQueries: RecipeIngredientQueries by inject()
    private val saveRecipe: SaveRecipe by inject()
    private val getRecipe: GetRecipe by inject()


    @BeforeTest
    fun setup() = runTest {
        writeHead("setup")
        ingredientQueries.deleteAllIngredients()
        recipeQueries.deleteAllRecipes()
        recipeIngredientQueries.deleteAllRecipeIngredients()

        val ingredients = IngredientMock.ingredientDbList
        val recipes = RecipeMock.recipeListDb
        val recipeIngredients = RecipeIngredientMock.recipeIngredientDbList


        for (ingredient in ingredients) {
            ingredientQueries.insertIngredient(ingredient)
        }

        for (recipe in recipes) {
            recipeQueries.insertRecipe(recipe)
        }

        for (recipeIngredient in recipeIngredients) {
            recipeIngredientQueries.insertRecipeIngredient(recipeIngredient)
        }
    }


    /**
     * should save a new recipeIngredient to the database
     * because the recipeId and the ingredientId are not the same
     */
    @Test
    fun save_recipe_success() = runTest {
        writeHead("save_recipe_success")

        for (recipe in recipeQueries.getAllRecipes()) {
            println(recipe.toString())
        }

        saveRecipe.execute(RecipeMock.recipe)

        for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        assertEquals(
            expected = RecipeMock.recipe,
            actual = getRecipe.execute(recipeQueries.getAllRecipes().last().id),
        )
    }


    /**
     * should update the recipeIngredient in the database
     * because the recipeId and the ingredientId are the same
     */
    @Test
    fun update_recipe_success() = runTest {
        writeHead("update_recipe_success")

        for (recipe in recipeQueries.getAllRecipes()) {
            println(recipe.toString())
        }

        saveRecipe.execute(RecipeMock.recipeUpdate)

        for (recipeIngredient in recipeIngredientQueries.getAllRecipeIngredients()) {
            println(recipeIngredient.toString())
        }

        assertEquals(
            expected = RecipeMock.recipeUpdate,
            actual = getRecipe.execute(RecipeMock.recipeUpdate.internalId!!),
        )
    }

}