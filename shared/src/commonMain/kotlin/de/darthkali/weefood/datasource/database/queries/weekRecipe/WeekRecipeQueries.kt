package de.darthkali.weefood.datasource.database.queries.weekRecipe

import de.darthkali.weefood.datasource.database.dao.WeekRecipeDb
import de.darthkali.weefood.domain.util.enums.Weekday

interface WeekRecipeQueries {
    fun insertWeekRecipe(weekRecipeDb: WeekRecipeDb): Boolean
    fun getAllWeekRecipes(): List<WeekRecipeDb>
    fun getAllWeekRecipesByWeekDay(weekday: Weekday): List<WeekRecipeDb>
    fun deleteWeekRecipeById(recipeId: Int): Boolean
    fun deleteAllWeekRecipe(): Boolean
}