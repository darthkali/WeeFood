package de.darthkali.weefood.datasource.database.weekRecipe

import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.toWeekRecipeList
import de.darthkali.weefood.domain.model.WeekRecipe
import de.darthkali.weefood.domain.util.Weekday
import de.darthkali.weefood.util.Logger

class WeekRecipeDbImpl(
    var weeFoodDatabase: WeeFoodDatabase
) : WeekRecipeDb {

    private val logger = Logger("WeekRecipeDbImpl")
    override fun insertWeekRecipe(weekRecipe: WeekRecipe): Boolean {
        return try {
            weeFoodDatabase.weekRecipeDbQueries.insertWeekRecip(
                null,
                weekday = weekRecipe.weekday.ordinal, // TODO: SQL Delight bietet eine möglichkeit enums zu nutzen. Das muss hier eingebaut werden
                portion = weekRecipe.portion,
                recipe_id = weekRecipe.recipe_id,
            )
            logger.log("Inserting with RecipeId: ${weekRecipe.recipe_id} into database")
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun getAllWeekRecipes(): List<WeekRecipe> {
        return try {
            logger.log("Get All WeekRecipes from database")
            weeFoodDatabase.weekRecipeDbQueries.getAllWeekRecipes()
                .executeAsList().toWeekRecipeList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun getAllWeekRecipesByWeekDay(weekday: Weekday): List<WeekRecipe> {
        return try {
            logger.log("Get All WeekRecipe from database by WeekDay")
            weeFoodDatabase.weekRecipeDbQueries.getAllWeekRecipesByWeekDay(
                weekday = weekday.ordinal // TODO: SQL Delight bietet eine möglichkeit enums zu nutzen. Das muss hier eingebaut werden
            ).executeAsList().toWeekRecipeList()
        } catch (e: Exception) {
            logger.log(e.toString())
            listOf()
        }
    }

    override fun deleteWeekRecipeById(recipeId: Int): Boolean {
        return try {
            logger.log("Delete WeekRecipe from database by ID")
            weeFoodDatabase.weekRecipeDbQueries.deleteWeekRecipeById(id = recipeId.toLong())
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }

    override fun deleteAllWeekRecipe(): Boolean {
        return try {
            logger.log("Delete all WeekRecipes from database")
            weeFoodDatabase.weekRecipeDbQueries.deleteAllWeekRecipes()
            true
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }
}