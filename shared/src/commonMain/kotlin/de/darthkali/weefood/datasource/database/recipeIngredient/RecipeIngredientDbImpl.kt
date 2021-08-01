package de.darthkali.weefood.datasource.database.recipeIngredient

import de.darthkali.weefood.datasource.database.RecipeIngredient_Entity
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import de.darthkali.weefood.domain.model.RecipeIngredient
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RecipeIngredientDbImpl: RecipeIngredientDb , KoinComponent {

    private val weeFoodDatabase: WeeFoodDatabaseWrapper by inject()
    private val weeFoodDatabaseQueries = weeFoodDatabase.instance.recipeIngredientDbQueries
    private val logger = Logger("RecipeIngredientDbImpl")

    override fun insertRecipeIngredient(recipeIngredient: RecipeIngredient): Boolean {
        return try {
            weeFoodDatabaseQueries.insertRecipeIngredient(
                null,
                quantity = recipeIngredient.quantity,
                unit = recipeIngredient.unit,
                recipe_id = recipeIngredient.recipe_id,
                ingredient_id = recipeIngredient.ingredient_id,
            )
            logger.log("Inserting with RecipeId: ${recipeIngredient.recipe_id} into database")
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun getAllRecipeIngredientByRecipeId(recipeId: Int): List<RecipeIngredient> {
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

    override fun getAllRecipeIngredients(): List<RecipeIngredient> {
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

    fun RecipeIngredient_Entity.toRecipeIngredient(): RecipeIngredient {
        return RecipeIngredient(
            id = id.toInt(),
            quantity = quantity,
            unit = unit,
            recipe_id = recipe_id,
            ingredient_id = ingredient_id,
        )
    }

    fun List<RecipeIngredient_Entity>.toRecipeIngredientList(): List<RecipeIngredient> {
        return map { it.toRecipeIngredient() }
    }

}