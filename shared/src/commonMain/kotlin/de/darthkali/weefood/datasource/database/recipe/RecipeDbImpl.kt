package de.darthkali.weefood.datasource.database.recipe

import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseFactory
import de.darthkali.weefood.datasource.database.toRecipe
import de.darthkali.weefood.datasource.database.toRecipeList
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipeDbImpl: RecipeDb , KoinComponent {

    private val weeFoodDatabaseFactory: WeeFoodDatabaseFactory by inject()
    private val weeFoodDatabase = weeFoodDatabaseFactory.createDatabase()
    private val logger = Logger("RecipeDbImpl")

    override fun insertRecipe(recipe: Recipe): Boolean {
        return try {
            weeFoodDatabase.recipeDbQueries.insertRecipe(
                null,
                name = recipe.name,
                image = recipe.image ?: "no.jpg",
                cooking_time = recipe.cooking_time,
                cooking_time_unit = recipe.cooking_time_unit,
                description = recipe.description,
            )
            logger.log("Inserting ${recipe.name} into database")
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun getAllRecipes(): List<Recipe> {
        return try {
            logger.log("Get All Recipes from database")
            weeFoodDatabase.recipeDbQueries.getAllRecipes(
                pageSize = 100,  // TODO replace with parameter
                offset = 0       // TODO replace with parameter
            ).executeAsList().toRecipeList()

        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun getRecipeById(recipeId: Int): Recipe? {
        return try {
            logger.log("Get Recipe from database by ID")
            weeFoodDatabase.recipeDbQueries.getRecipeById(
                id = recipeId.toLong()
            ).executeAsOne().toRecipe()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun searchRecipes(name: String): List<Recipe> {
        return try {
            logger.log("Search Recipes in database")
            weeFoodDatabase.recipeDbQueries.searchRecipes(
                query = name,
                pageSize = 100,  // TODO replace with parameter
                offset = 0       // TODO replace with parameter
            ).executeAsList().toRecipeList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun deleteRecipeById(recipeId: Int): Boolean {
        return try {
            logger.log("Delete Recipe from database by ID")
            weeFoodDatabase.recipeDbQueries.deleteRecipeById(id = recipeId.toLong())
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun deleteAllRecipes(): Boolean {
        return try {
            logger.log("Delete all Recipes from database")
            weeFoodDatabase.recipeDbQueries.deleteAllRecipes()
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }
}
