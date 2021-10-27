package de.darthkali.weefood.datasource.database.repository.recipe

import de.darthkali.weefood.datasource.database.Recipe_Entity
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipeRepositoryImpl : RecipeRepository, KoinComponent {

    private val weeFoodDatabase: WeeFoodDatabaseWrapper by inject()
    private val weeFoodDatabaseQueries = weeFoodDatabase.instance.recipeDbQueries
    private val logger = Logger("RecipeRepositoryImpl")

    override fun insertRecipe(recipeDb: RecipeDb): Int? {
        return try {
            weeFoodDatabaseQueries.insertRecipe(
                null,
                name = recipeDb.name,
                image = recipeDb.image ?: "no.jpg",
                cooking_time = recipeDb.cooking_time,
                cooking_time_unit = recipeDb.cooking_time_unit,
                description = recipeDb.description,
            )
            logger.log("Inserting ${recipeDb.name} into database")
            weeFoodDatabaseQueries.getLastInsertRowId().executeAsOne().toInt()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun updateRecipe(recipeDb: RecipeDb): Int? {
        return try {
            weeFoodDatabaseQueries.updateRecipe(
                name = recipeDb.name,
                image = recipeDb.image ?: "no.jpg",
                cooking_time = recipeDb.cooking_time,
                cooking_time_unit = recipeDb.cooking_time_unit,
                description = recipeDb.description,
                id = recipeDb.id.toLong()
            )
            logger.log("Updated ${recipeDb.name} in database")
            recipeDb.id
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun getAllRecipes(): List<RecipeDb> {
        return try {
            logger.log("Get All Recipes from database")
            weeFoodDatabaseQueries.getAllRecipes(
                pageSize = 100, // TODO replace with parameter
                offset = 0 // TODO replace with parameter
            ).executeAsList().toRecipeList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun getRecipeById(recipeId: Int): RecipeDb? {
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

    override fun getLastInsertRowId(): Int? {
        return try {
            logger.log("Get last insert row id")
            weeFoodDatabaseQueries.getLastInsertRowId().executeAsOne().toInt()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun searchRecipes(name: String, page: Int): List<RecipeDb> {
        return try {
            logger.log("Search Recipes in database")
            weeFoodDatabaseQueries.searchRecipes(
                query = name,
                pageSize = 30.toLong(),
                offset = ((page - 1) * 30).toLong(), // TODO: Replace 30 with Pagination Size
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

    fun Recipe_Entity.toRecipe(): RecipeDb {
        return RecipeDb(
            id = id.toInt(),
            name = name,
            image = image,
            cooking_time = cooking_time,
            cooking_time_unit = cooking_time_unit,
            description = description
        )
    }

    fun List<Recipe_Entity>.toRecipeList(): List<RecipeDb> {
        return map { it.toRecipe() }
    }
}
