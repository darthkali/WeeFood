package de.darthkali.weefood.datasource.database.recipe

import de.darthkali.weefood.datasource.database.Recipe_Entity
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipeDbImpl: RecipeDb , KoinComponent {

    private val weeFoodDatabase: WeeFoodDatabaseWrapper by inject()
    private val weeFoodDatabaseQueries = weeFoodDatabase.instance.recipeDbQueries

    private val logger = Logger("RecipeDbImpl")

    override fun insertRecipe(recipe: Recipe): Boolean {
        return try {
            weeFoodDatabaseQueries.insertRecipe(
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
            weeFoodDatabaseQueries.getAllRecipes(
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
            weeFoodDatabaseQueries.getRecipeById(
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
            weeFoodDatabaseQueries.searchRecipes(
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
            weeFoodDatabaseQueries.deleteRecipeById(id = recipeId.toLong())
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun deleteAllRecipes(): Boolean {
        return try {
            logger.log("Delete all Recipes from database")
            weeFoodDatabaseQueries.deleteAllRecipes()
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    /*
-- -----------------------------------------------------
-- recipe_Entity
-- -----------------------------------------------------
  id            INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT,
  name          TEXT                NOT NULL,
  image         TEXT,
  cooking_time  INTEGER AS Integer  NOT NULL,
  unit          TEXT                NOT NULL,
  description   TEXT
-- -----------------------------------------------------
*/

    fun Recipe_Entity.toRecipe(): Recipe {
        return Recipe(
            id = id.toInt(),
            name = name,
            image = image,
            cooking_time = cooking_time,
            cooking_time_unit = cooking_time_unit,
            description = description
        )
    }

    fun List<Recipe_Entity>.toRecipeList(): List<Recipe> {
        return map { it.toRecipe() }
    }


}
