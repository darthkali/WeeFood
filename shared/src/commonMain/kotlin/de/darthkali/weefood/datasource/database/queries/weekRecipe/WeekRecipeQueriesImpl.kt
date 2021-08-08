package de.darthkali.weefood.datasource.database.queries.weekRecipe

import de.darthkali.weefood.datasource.database.RecipeIngredient_Entity
import de.darthkali.weefood.datasource.database.WeekRecipe_Entity
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseWrapper
import de.darthkali.weefood.datasource.database.model.RecipeIngredientDb
import de.darthkali.weefood.datasource.database.dao.WeekRecipeDb
import de.darthkali.weefood.domain.util.enums.Weekday
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeekRecipeQueriesImpl: WeekRecipeQueries, KoinComponent {

    private val weeFoodDatabase: WeeFoodDatabaseWrapper by inject()
    private val weeFoodDatabaseQueries = weeFoodDatabase.instance.weekRecipeDbQueries
    private val logger = Logger("WeekRecipeQueriesImpl")

    override fun insertWeekRecipe(weekRecipeDb: WeekRecipeDb): Boolean {
        return try {
            weeFoodDatabaseQueries.insertWeekRecip(
                null,
                weekday = weekRecipeDb.weekday.value, // TODO: SQL Delight bietet eine möglichkeit enums zu nutzen. Das muss hier eingebaut werden
                portion = weekRecipeDb.portion,
                recipe_id = weekRecipeDb.recipe_id,
            )
            logger.log("Inserting with RecipeId: ${weekRecipeDb.recipe_id} into database")
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun getAllWeekRecipes(): List<WeekRecipeDb> {
        return try {
            logger.log("Get All WeekRecipes from database")
            weeFoodDatabaseQueries.getAllWeekRecipes()
                .executeAsList().toWeekRecipeList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun getAllWeekRecipesByWeekDay(weekday: Weekday): List<WeekRecipeDb> {
        return try {
            logger.log("Get All WeekRecipe from database by WeekDay")
            weeFoodDatabaseQueries.getAllWeekRecipesByWeekDay(
                weekday = weekday.value, //.ordinal // TODO: SQL Delight bietet eine möglichkeit enums zu nutzen. Das muss hier eingebaut werden
            ).executeAsList().toWeekRecipeList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun deleteWeekRecipeById(recipeId: Int): Boolean {
        return try {
            logger.log("Delete WeekRecipe from database by ID")
            weeFoodDatabaseQueries.deleteWeekRecipeById(id = recipeId.toLong())
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun deleteAllWeekRecipe(): Boolean {
        return try {
            logger.log("Delete all WeekRecipes from database")
            weeFoodDatabaseQueries.deleteAllWeekRecipes()
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


/*
-- -----------------------------------------------------
-- weekRecipe_Entity
-- -----------------------------------------------------
  id        INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT,
  weekday   INTEGER AS Integer  NOT NULL,
  portion   INTEGER AS Integer  NOT NULL,
  recipe_id INTEGER AS Integer  NOT NULL,

  FOREIGN KEY (recipe_id) REFERENCES recipe_Entity(id)
-- -----------------------------------------------------
*/

    fun WeekRecipe_Entity.toWeekRecipe(): WeekRecipeDb {
        return WeekRecipeDb(
            id = id.toInt(),
            weekday = Weekday.fromInt(weekday),
            portion = portion,
            recipe_id = recipe_id,
        )
    }

    fun List<WeekRecipe_Entity>.toWeekRecipeList(): List<WeekRecipeDb> {
        return map { it.toWeekRecipe() }
    }

}