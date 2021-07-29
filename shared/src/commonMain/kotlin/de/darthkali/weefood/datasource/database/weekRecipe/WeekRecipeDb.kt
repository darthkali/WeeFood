package de.darthkali.weefood.datasource.database.weekRecipe

import de.darthkali.weefood.domain.model.WeekRecipe

interface WeekRecipeDb {
    fun insertWeekRecipe(weekRecipe: WeekRecipe): Boolean
    fun getAllWeekRecipes(): List<WeekRecipe>
    fun getAllWeekRecipesByWeekDay(weekday: Int): List<WeekRecipe>
    fun deleteWeekRecipeById(recipeId: Int): Boolean
    fun deleteAllWeekRecipe(): Boolean
}