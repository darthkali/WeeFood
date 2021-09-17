package de.darthkali.weefood.datasource.database.repository.weekRecipe

import de.darthkali.weefood.datasource.database.model.WeekRecipeDb
import de.darthkali.weefood.domain.util.enums.Weekday

interface WeekRecipeRepository {
    fun insertWeekRecipe(weekRecipeDb: WeekRecipeDb): Boolean
    fun getAllWeekRecipes(): List<WeekRecipeDb>
    fun getAllWeekRecipesByWeekDay(weekday: Weekday): List<WeekRecipeDb>
    fun deleteWeekRecipeById(recipeId: Int): Boolean
    fun deleteAllWeekRecipe(): Boolean
}