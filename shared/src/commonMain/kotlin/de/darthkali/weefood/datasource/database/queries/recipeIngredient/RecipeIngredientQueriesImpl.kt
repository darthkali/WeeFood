package de.darthkali.weefood.datasource.database.queries.recipeIngredient

import de.darthkali.weefood.datasource.database.RecipeIngredient_Entity
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RecipeIngredientQueriesImpl: RecipeIngredientQueries, KoinComponent {

    private val weeFoodDatabase: WeeFoodDatabaseWrapper by inject()
    private val weeFoodDatabaseQueries = weeFoodDatabase.instance.recipeIngredientDbQueries
    private val logger = Logger("RecipeIngredientDbImpl")

    override fun insertRecipeIngredient(recipeIngredientDb: RecipeIngredientDb): Int? {
        return try {
            weeFoodDatabaseQueries.insertRecipeIngredient(
                null,
                quantity = recipeIngredientDb.quantity,
                unit = recipeIngredientDb.unit,
                recipe_id = recipeIngredientDb.recipe_id,
                ingredient_id = recipeIngredientDb.ingredient_id,
            )
            logger.log("Inserting RecipeIngredient with RecipeId: ${recipeIngredientDb.recipe_id} into database")
            weeFoodDatabaseQueries.getLastInsertRowId().executeAsOne().toInt()
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun updateRecipeIngredient(recipeIngredientDb: RecipeIngredientDb): Int? {
        return try {
            weeFoodDatabaseQueries.updateRecipeIngredient(
                quantity = recipeIngredientDb.quantity,
                unit = recipeIngredientDb.unit,
                recipe_id = recipeIngredientDb.recipe_id,
                ingredient_id = recipeIngredientDb.ingredient_id,
                id = recipeIngredientDb.id.toLong()
            )
            logger.log("Updated RecipeIngredient with RecipeId: ${recipeIngredientDb.recipe_id} into database")
            recipeIngredientDb.id
        } catch (e: Exception) {
            logger.log(e.toString())
            null
        }
    }

    override fun getAllRecipeIngredientByRecipeId(recipeId: Int): List<RecipeIngredientDb> {
        return try {
            logger.log("Get RecipeIngredient from database by ID")
            weeFoodDatabaseQueries.getAllRecipeIngredientsByRecipeId(
                recipe_id = recipeId
            ).executeAsList().toRecipeIngredientList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun getAllRecipeIngredients(): List<RecipeIngredientDb> {
        return try {
            logger.log("Get all RecipeIngredients from database")
            weeFoodDatabaseQueries.getAllRecipeIngredients()
                .executeAsList().toRecipeIngredientList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun deleteRecipeIngredientById(recipeId: Int): Boolean {
        return try {
            logger.log("Delete RecipeIngredient from database by ID")
            weeFoodDatabaseQueries.deleteRecipeIngredientById(id = recipeId.toLong())
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun deleteAllRecipeIngredients(): Boolean {
        return try {
            logger.log("Delete all RecipeIngredients from database")
            weeFoodDatabaseQueries.deleteAllRecipeIngredients()
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    /*
-- -----------------------------------------------------
-- recipeIngredient_Entity
-- -----------------------------------------------------
  id            INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT,
  quantity      REAL AS Float       NOT NULL,
  unit          TEXT                NOT NULL,
  recipe_id     INTEGER AS Integer  NOT NULL,
  ingredient_id INTEGER AS Integer  NOT NULL,

  FOREIGN KEY (ingredient_id)   REFERENCES ingredient_Entity(id),
  FOREIGN KEY (recipe_id)       REFERENCES recipe_Entity(id)
-- -----------------------------------------------------
*/

    fun RecipeIngredient_Entity.toRecipeIngredient(): RecipeIngredientDb {
        return RecipeIngredientDb(
            id = id.toInt(),
            quantity = quantity,
            unit = unit,
            recipe_id = recipe_id,
            ingredient_id = ingredient_id,
        )
    }

    fun List<RecipeIngredient_Entity>.toRecipeIngredientList(): List<RecipeIngredientDb> {
        return map { it.toRecipeIngredient() }
    }

}