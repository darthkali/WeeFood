package de.darthkali.weefood.datasource.database.weekRecipe

import de.darthkali.weefood.datasource.database.WeeFoodDatabase
import de.darthkali.weefood.datasource.database.WeeFoodDatabaseFactory
import de.darthkali.weefood.datasource.database.toWeekRecipeList
import de.darthkali.weefood.domain.model.WeekRecipe
import de.darthkali.weefood.domain.util.enums.Weekday
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeekRecipeDbImpl: WeekRecipeDb , KoinComponent {

    private val weeFoodDatabaseFactory: WeeFoodDatabaseFactory by inject()
    private val weeFoodDatabase = weeFoodDatabaseFactory.createDatabase()
    private val logger = Logger("WeekRecipeDbImpl")

    override fun insertWeekRecipe(weekRecipe: WeekRecipe): Boolean {
        return try {
            weeFoodDatabase.weekRecipeDbQueries.insertWeekRecip(
                null,
                weekday = weekRecipe.weekday.value, // TODO: SQL Delight bietet eine möglichkeit enums zu nutzen. Das muss hier eingebaut werden
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